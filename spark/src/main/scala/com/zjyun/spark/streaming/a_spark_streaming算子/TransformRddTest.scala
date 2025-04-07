package com.zjyun.spark.streaming.a_spark_streaming算子

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.ReceiverInputDStream

/**
 * transform
 */
object TransformRddTest {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("sparkStreaming")
    val ssc = new StreamingContext(conf, Seconds(1))
    val receiverInputDStream: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 6666)

    /**
     * 将函数应用于此DStream中的每个RDD,返回的还是一个DStream但是后续还可以使用。
     */
    receiverInputDStream.transform((rdd, time) => {
      rdd.flatMap(_.split(" "))
        .map((_, 1))
        .reduceByKey(_ + _)
    }).print()
    ssc.start()
    ssc.awaitTermination()
  }
}
