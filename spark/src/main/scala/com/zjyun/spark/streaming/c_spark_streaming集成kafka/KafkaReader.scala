package com.zjyun.spark.streaming.c_spark_streaming集成kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object KafkaReader {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("KafkaReader")
    val sparkContext = new SparkContext(sparkConf)
    val streamingContext =
      new StreamingContext(sparkContext = sparkContext, batchDuration = Seconds(5))


    //kafka 连接参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.56.98:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "user",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("name")
    val stream: DStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    stream.map(record => (record.key, record.value))
      .foreachRDD((rdd, time) => {
        rdd.map(_._2)
          .foreach(println)
      })
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
