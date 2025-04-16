package com.zjyun.spark.structured_streaming

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{count, sum}

/**
 * 使用Structured-Streaming-WordCount
 * nc -l -k -p 6666
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Structured-Streaming-WordCount")
      .master("local[2]")
      .getOrCreate()
    import spark.implicits._

    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 6666)
      .load()

    // 分割单词
    val words = lines.as[String].flatMap(_.split(" "))
    // 生成运行字数
/*    val wordCounts = words.groupBy("value")
      .agg(count("value")as("单词出现的个数"))
      .withColumnRenamed("value","单词")*/

    //开始运行将运行计数打印到控制台的查询
    val query = words.writeStream
      .outputMode("complete")
      .format("console")
      .start()
    query.awaitTermination()
  }
}
