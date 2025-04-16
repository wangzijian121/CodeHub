package com.zjyun.spark.structured_streaming.窗口函数

import org.apache.spark.sql.SparkSession
import java.sql.Timestamp
import org.apache.spark.sql.functions.window

/**
 * 使用Structured-Streaming-WordCount
 * nc -l -k -p 6666
 */
object WordCountWithWindow {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("WordCountWithWindow")
      .master("local[2]")
      .getOrCreate()
    import spark.implicits._

    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 6666)
      .option("includeTimestamp", true)
      .load()

    // 分割单词
    // 将行拆分为单词，保留时间戳
    val words = lines.as[(String, Timestamp)].flatMap(line =>
      line._1.split(" ").map(word => (word, line._2))
    ).toDF("word", "timestamp")

    //按 window 和 word 对数据进行分组，并计算每组的计数
    val windowedCounts = words.groupBy(
      window($"timestamp", "10 minutes", "5 minutes"),
      $"word"
    ).count().orderBy("window")

    //开始运行将运行计数打印到控制台的查询
    val query = windowedCounts.writeStream
      .outputMode("complete")
      .format("console")
      .option("truncate", "false")
      .start()
    query.awaitTermination()
  }
}
