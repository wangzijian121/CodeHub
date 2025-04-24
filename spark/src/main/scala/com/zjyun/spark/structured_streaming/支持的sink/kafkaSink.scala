package com.zjyun.spark.structured_streaming.支持的sink

import org.apache.spark.sql.SparkSession

/**
 * Kafka输出
 */
object kafkaSink {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("Kafka输出").master("local[*]").getOrCreate()
    import sparkSession.implicits._
    val dataFrame = sparkSession.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 8888)
      .load()

    // 构建 Kafka 的 key 和 value（必须为字符串或字节数组）
    val kafkaDF = dataFrame.selectExpr(
      "CAST(value AS STRING)"
    )
    kafkaDF.writeStream
      .format("kafka")
      .outputMode("append")
      .option("kafka.bootstrap.servers", "192.168.56.98:9092")
      .option("topic", "test")
      .option("checkpointLocation", "ckpt/kafkaCheckpoint")
      .start()
      .awaitTermination()
  }
}