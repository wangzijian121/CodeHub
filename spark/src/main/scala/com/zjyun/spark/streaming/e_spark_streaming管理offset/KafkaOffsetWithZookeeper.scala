package com.zjyun.spark.streaming.e_spark_streaming管理offset

import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import scala.collection.JavaConverters._
import scala.collection.mutable

/**
 * 使用zookeeper 存储偏移量
 * 思路：
 * 1. 首先判断zk中的指定topic下是否有分区目录
 * 2. 有的话读取，没有的话创建。
 * 3. 在处理完rdd后，将偏移量写入到：/kafka_offset/{topic}/{分区} 内
 */
object KafkaOffsetWithZookeeper {
  //zookeeper 配客户端
  private val zkConnect = "hadoop01:2181"
  //重试策略
  private val retryPolicy = new ExponentialBackoffRetry(1000, 3)
  private val zkClient = CuratorFrameworkFactory.newClient(zkConnect, retryPolicy)
  zkClient.start()

  //kafka 连接参数
  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "192.168.56.98:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "user",
    "auto.offset.reset" -> "earliest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  private def checkExists(path: String): Boolean = zkClient.checkExists().forPath(path) != null

  /**
   * 启动时从ZK中获取偏移量
   */
  private def getOffsetFromZk(topics: Array[String]): mutable.Map[TopicPartition, Long] = {

    val offsets = mutable.Map[TopicPartition, Long]()
    val kafkaConsumer = new KafkaConsumer(kafkaParams.asJava)

    topics.foreach(topic => {
      val topicPath = s"/kafka_offset/$topic"
      //判断zk中是否有偏移量信息
      if (checkExists(topicPath) != null) {
        val partitionList = zkClient.getChildren.forPath(topicPath)
        partitionList.forEach(partition => {
          val offsetValue = new String(zkClient.getData().forPath(topicPath + "/" + partition)).toLong
          offsets.put(new TopicPartition(topic, partition.toInt), offsetValue)
        })
      }
    })
    //防止kafka数据过期问题，如果kafka中的偏移量大于zk中的偏移量，则取kafka中的
    val kafkaTopicMap = kafkaConsumer.beginningOffsets(offsets.keys.asJavaCollection)
    println("kafka中的偏移量" + kafkaTopicMap)
    offsets
  }

  /**
   * 更新ZK中的偏移量
   */
  private def updateOffsetFromZk(path: String, data: Array[Byte]): Unit = {

    if (checkExists(path) != null) {
      //如果存在则更新
      zkClient.setData().forPath(path, data)
    } else {
      //如果不存在,则创建
      zkClient.create()
        .creatingParentContainersIfNeeded()
        .forPath(path, data)
    }
  }


  def main(args: Array[String]): Unit = {
    //spark参数
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaReader")
    val ssc = new StreamingContext(sparkConf, Seconds(10))


    val topics = Array("name")
    val stream: DStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams, getOffsetFromZk(topics))
    )

    stream
      .foreachRDD(rdd => {
        // 获取当前批次的偏移量范围
        val offsetRanges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        getOffsetFromZk(topics)
        offsetRanges.foreach(offsetRange => {
          val topic = offsetRange.topic
          val partition = offsetRange.partition
          val fromOffset = offsetRange.fromOffset //起始偏移量
          val untilOffset = offsetRange.untilOffset //结束偏移量

          println(s"topic：$topic 分区：$partition 起始偏移量：$fromOffset 结束偏移量：$untilOffset")
          val path = s"/kafka_offset/$topic/$partition"
          val data = untilOffset.toString.getBytes("UTF-8")
          updateOffsetFromZk(path, data)
        })
        rdd.foreach(println)
      })
    ssc.start()
    ssc.awaitTermination()
    zkClient.close()
  }
}
