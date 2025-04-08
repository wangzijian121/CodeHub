package com.zjyun.spark.streaming.a_spark_streaming算子

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.ReceiverInputDStream

/**
 * 使用 checkpoint 来保存历史数据
 */
object UpdateStateByKeyCheckPointTest {
  def main(args: Array[String]): Unit = {

    val ckptPath = "./ckpt"
    val conf = new SparkConf().setMaster("local[2]").setAppName("sparkStreaming")
    //如果ckptPath 路径有历史数据则加载，否则走自行创建StreamingContext的逻辑
    val ssc = StreamingContext.getActiveOrCreate(ckptPath, () => {
      val ssc = new StreamingContext(conf, Seconds(5))
      //设置checkpoint文件夹
      ssc.checkpoint("./ckpt")
      val receiverInputDStream: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 6666)
      receiverInputDStream.transform((rdd, time) => {
          rdd.flatMap(_.split(" "))
            .map((_, 1))
            .reduceByKey(_ + _)
        })
        //curr为同一个key对应的值列表，last为历史值
        .updateStateByKey((curr: Seq[Int], last: Option[Int]) => Option(curr.sum + last.getOrElse(0))).print()
      ssc
    })
    ssc.start()
    ssc.awaitTermination()
  }
}