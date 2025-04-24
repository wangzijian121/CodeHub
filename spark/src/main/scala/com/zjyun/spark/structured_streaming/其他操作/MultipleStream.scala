package com.zjyun.spark.structured_streaming.其他操作

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

import java.sql.Timestamp

/**
 * 多重watermark的处理策略
 */
object MultipleStream {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("去重")
      .master("local[2]").getOrCreate()
    import sparkSession.implicits._
    val in = sparkSession.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 6666)
      .load()

    val in2 = sparkSession.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 7777)
      .load()

    //1,aaa,2024-08-09 12:00:10
    //1,aaa,2024-08-09 12:05:10
    //1,aaa,2024-08-09 12:20:10
    val s1 = in.as[String].map(line => {
      val s = line.split(",")
      (s(0), s(1), Timestamp.valueOf(s(2)))
    }).toDF("id", "name", "t1").withWatermark("t1", "1 minutes")

    //1,bbb,2024-08-09 11:00:10
    //1,bbb,2024-08-09 12:08:10
    //1,bbb,2024-08-09 12:12:10
    //1,bbb,2024-08-09 11:59:10
    val s2 = in2.as[String].map(line => {
      val s = line.split(",")
      (s(0), s(1), Timestamp.valueOf(s(2)))
    }).toDF("id", "name", "t2").withWatermark("t2", "10 minutes")

    val res = s1.join(s2, "id")
    res.writeStream
      .outputMode(OutputMode.Append())
      .format("console")
      .option("truncate", false)
      .start()
      .awaitTermination()
  }
}
