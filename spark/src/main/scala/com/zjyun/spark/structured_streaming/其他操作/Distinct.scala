package com.zjyun.spark.structured_streaming.其他操作

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

import java.sql.Timestamp

object Distinct {
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

    //1,aaa,2024-08-09 12:00:10 第一条
    //1,aaa,2024-08-09 12:00:10 第一条（不会显示）
    val res = in.as[String].distinct()
    res.explain(true)
    res.writeStream
      .outputMode(OutputMode.Append())
      .format("console")
      .option("truncate", false)
      .start()
      .awaitTermination()
  }
}
