package com.zjyun.spark.structured_streaming.操作方式.api方式

import org.apache.spark.sql.SparkSession

/**
 * 使用rate 的性能基准测试
 */
object kafkaStreaming {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("structured_streaming读取kafka")
      .getOrCreate()
    import spark.implicits._

    // 订阅 1 个主题
    val dataFrame = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "192.168.56.98:9092")
      .option("subscribe", "name")
      .load()
    val frame1 = dataFrame.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]
      .select("value").as[String]
      .map(t => {
        val s = t.split(" ")
        (s(0), s(1), s(2))
      }).toDF("id", "name", "age")
    //API的方式
    val frame2 = frame1.groupBy("age").count()

    //获取输出流
    frame2.writeStream
      .outputMode("complete")
      .format("console")
      .start()
      .awaitTermination()
  }
}
