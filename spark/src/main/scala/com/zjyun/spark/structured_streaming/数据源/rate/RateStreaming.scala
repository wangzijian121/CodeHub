package com.zjyun.spark.structured_streaming.数据源.rate

import org.apache.spark.sql.types.{ArrayType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * 使用rate 的性能基准测试
 */
object RateStreaming {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[2]")
      .appName("structured_streaming读取text")
      .getOrCreate()

    //获取rate输入流
    val dataFrame: DataFrame = sparkSession.readStream
      .format("rate")
      .option("rowsPerSecond",10)//每秒应生成多少行
      .option("numPartitions",4)//分区个数
      .load()


    //获取输出流
    dataFrame.writeStream
      .outputMode("update")
      .format("console")
      .start()
      .awaitTermination()
  }
}
