package com.zjyun.spark.streaming.wordcount

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream} // not necessary since Spark 1.3

object AddOne {
  def main(args: Array[String]): Unit = {
    //创建一个具有两个工作线程且批处理间隔为 1 秒的本地 StreamingContext。
    //主服务器需要 2 个内核来防止出现匮乏情况。
    val conf = new SparkConf().setMaster("local[2]").setAppName("sparkStreaming")
    val ssc = new StreamingContext(conf, Seconds(1))
    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 6666)
    val value: DStream[Int] = lines.map(_.toInt + 1)
    value.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
