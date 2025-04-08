package com.zjyun.spark.streaming.e_spark_streaming管理offset

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 使用kafka自身的API 存储偏移量
 */
object KafkaOffsetWithKafkaApi {
  def main(args: Array[String]): Unit = {
    //spark参数
    val path = "./kafka_ckpt"
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaReader")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    //kafka 连接参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.56.98:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "user",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    //读取ckpt
    ssc.checkpoint(path)
    val topics = Array("name")
    val stream: DStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    stream
      .foreachRDD(rdd=> {
        // 获取当前批次的偏移量范围
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        rdd.foreach(println)
        //一段时间后，在输出完成后
        stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
      })

    ssc.start()
    ssc.awaitTermination()
  }
}
