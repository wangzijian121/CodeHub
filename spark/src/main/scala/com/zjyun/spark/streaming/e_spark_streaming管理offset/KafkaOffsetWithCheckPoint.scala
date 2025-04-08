package com.zjyun.spark.streaming.e_spark_streaming管理offset

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 使用checkpoint 存储偏移量
 */
object KafkaOffsetWithCheckPoint {
  def main(args: Array[String]): Unit = {
    //spark参数
    val path = "./kafka_ckpt"
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaReader")

    //kafka 连接参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.56.98:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "user",
      //"auto.offset.reset" -> "latest",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )
    //读取ckpt
    val streamingContext = StreamingContext.getActiveOrCreate(path, () => {
      val ssc = new StreamingContext(sparkConf, Seconds(3))
      ssc.checkpoint(path)
      val topics = Array("name")
      val stream: DStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
        ssc,
        PreferConsistent,
        Subscribe[String, String](topics, kafkaParams)
      )
      stream.map(record => (record.key, record.value))
        .foreachRDD((rdd, time) => {
          rdd.map(_._2).foreach(println)
        })
      ssc
    })
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
