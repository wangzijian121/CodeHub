package com.zjyun.spark.streaming.d_spark_streaming动态更新广播变量

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

import java.util.Scanner
import scala.concurrent._
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * 动态更新广播变量
 */
object SparkStreamingBoardCast {
  val map = mutable.Map[String, String]()

  def updateCountry(): Unit = {
    //读取本地文件
    val fs = FileSystem.getLocal(new Configuration())
    val countrys = fs.listStatus(new Path("/home/wangzijian/code/code-hub/spark/country"))
    Future {
      while (true) {
        countrys.foreach(t => {
          val in = fs.open(t.getPath)
          val scanner = new Scanner(in)
          while (scanner.hasNext()) {
            val line = scanner.nextLine()
            val strs = line.split(",")
            map.put(strs(0), strs(1))
          }
        })
        Thread.sleep(1000)
      }
    }
  }

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
    //持续更新
    updateCountry()

    //广播变量
    var bs = streamingContext.sparkContext.broadcast(map)
    stream.map(record => (record.key, record.value))
      .foreachRDD(rdd => {
        rdd.map(_._2)
          .foreach(num => println(bs.value.getOrElse(num, "unkown")))
      })


    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
