package com.zjyun.spark.structured_streaming.数据源.json

import org.apache.spark.sql.types.{ArrayType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * 读取指定目录下的json文件
 * Batch: 0
 * -------------------------------------------
 * +----------+---+---------------+----------+
 * |      name|age|           love|     other|
 * +----------+---+---------------+----------+
 * |wangzijian| 18|[aaa, bbb, ccc]|[183, 200]|
 * +----------+---+---------------+----------+
 */
object JsonStreaming {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[2]")
      .appName("structured_streaming读取text")
      .getOrCreate()

    //创建schema
    //{"name":"wangzijian","age":18,"love":["aaa","bbb","ccc"],"other":{"high":183,"weight":200}}
    val schema: StructType = StructType(Seq(
      StructField("name", StringType),
      StructField("age", IntegerType),
      StructField("love", ArrayType(StringType)),
      StructField("other", StructType(Seq(
        StructField("high", IntegerType),
        StructField("weight", IntegerType),
      )),
      )))
    //获取输入流
    val dataFrame: DataFrame = sparkSession.readStream
      .format("json")
      .schema(schema)
      .option("path", "/home/wangzijian/datasource/spark/json")
      .load()

    //处理RDD

    //获取输出流
    dataFrame.writeStream
      .outputMode("update")
      .format("console")
      .start()
      .awaitTermination()
  }
}
