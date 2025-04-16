package com.zjyun.spark.structured_streaming.数据源.csv

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * 读取指定目录下的csv文件，增量并过滤后输出
 * +---+-------+
 * | id|country|
 * +---+-------+
 * |  1|   中国|
 * |  1|   中国|
 * +---+-------+
 */
object CsvStreaming {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[2]")
      .appName("structured_streaming读取text")
      .getOrCreate()

    //创建schema
    val schema: StructType = StructType(Array(
      StructField("id", IntegerType),
      StructField("country", StringType)
    ))
    //获取输入流
    val dataFrame: DataFrame = sparkSession.readStream
      .format("csv")
      .schema(schema)
      .option("path", "/home/wangzijian/datasource/spark/csv")
      .load()

    val dataFrameFilter= dataFrame.filter("country=='中国'")

    //获取输出流
    dataFrameFilter.writeStream
      .format("console")
      .start()
      .awaitTermination()
  }
}
