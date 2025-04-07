package com.zjyun.spark.streaming.a_spark_streaming算子

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.ReceiverInputDStream

/**
 * Windows 窗口函数
 * 需求：每5秒计算前30秒银行交易额
 * wangzijian:666 交易666 金额
 */
object WindowsTest {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("sparkStreaming")
    val ssc = new StreamingContext(conf, Seconds(5))
    val receiverInputDStream: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 6666)
    receiverInputDStream.window(Seconds(30)).foreachRDD((rdd, time) => {
      rdd.map(line => {
          val lineSplit = line.split(":")
          (lineSplit(0), lineSplit(1).toInt)
        })
        .reduceByKey(_ + _)
        .foreach(println)
    })
    ssc.start()
    ssc.awaitTermination()
  }
}
