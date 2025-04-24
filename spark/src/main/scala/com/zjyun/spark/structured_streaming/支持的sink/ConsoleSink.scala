package com.zjyun.spark.structured_streaming.支持的sink

import org.apache.spark.sql.SparkSession

/**
 * 控制台输出
 */
object ConsoleSink {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("<ConsoleSink>").master("local[2]").getOrCreate()
    val dataFrame = sparkSession.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 8888)
      .load()

    dataFrame.writeStream
      .outputMode("complete")
      .format("console")
      .option("truncate", false)
      .start()
      .awaitTermination()
  }
}
