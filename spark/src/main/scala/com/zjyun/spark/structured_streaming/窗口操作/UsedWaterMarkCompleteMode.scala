package com.zjyun.spark.structured_streaming.窗口操作

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.window
import org.apache.spark.sql.streaming.OutputMode

import java.sql.Timestamp

/**
 * spark 窗口函数（按照事件时间计算）
 * 全量模式
 *
 * 输入数据
 * 2023-02-13 12:07:10 dog
 * 2023-02-13 12:08:10 owl
 *
 * 2023-02-13 12:14:10 dog
 * 2023-02-13 12:09:10 cat
 *
 * 2023-02-13 12:15:10 cat
 * 2023-02-13 12:13:10 owl
 * 2023-02-13 12:08:10 dog
 * 2023-02-13 12:21:10 owl
 *
 * 2023-02-13 12:04:10 donkey
 *
 * donkey 还是会出现，证明watermark 并没有生效
 * +------------------------------------------+------+-----+
 * |window                                    |name  |count|
 * +------------------------------------------+------+-----+
 * |[2023-02-13 12:05:00, 2023-02-13 12:15:00]|owl   |2    |
 * |[2023-02-13 12:20:00, 2023-02-13 12:30:00]|owl   |1    |
 * |[2023-02-13 11:55:00, 2023-02-13 12:05:00]|donkey|1    |
 * |[2023-02-13 12:05:00, 2023-02-13 12:15:00]|dog   |3    |
 * |[2023-02-13 12:10:00, 2023-02-13 12:20:00]|dog   |1    |
 * |[2023-02-13 12:15:00, 2023-02-13 12:25:00]|owl   |1    |
 * |[2023-02-13 12:00:00, 2023-02-13 12:10:00]|dog   |2    |
 * |[2023-02-13 12:10:00, 2023-02-13 12:20:00]|cat   |1    |
 * |[2023-02-13 12:05:00, 2023-02-13 12:15:00]|cat   |1    |
 * |[2023-02-13 12:00:00, 2023-02-13 12:10:00]|owl   |1    |
 * |[2023-02-13 12:00:00, 2023-02-13 12:10:00]|donkey|1    |
 * |[2023-02-13 12:10:00, 2023-02-13 12:20:00]|owl   |1    |
 * |[2023-02-13 12:00:00, 2023-02-13 12:10:00]|cat   |1    |
 * |[2023-02-13 12:15:00, 2023-02-13 12:25:00]|cat   |1    |
 * +------------------------------------------+------+-----+
 *
 */
object UsedWaterMarkCompleteMode {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("使用watermark的")
      .getOrCreate()
    import spark.implicits._

    // 订阅 1 个主题
    val dataFrame = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "192.168.56.98:9092")
      .option("subscribe", "name")
      .load()

    //2023-02-13 12:02:11 cat1 dog1
    //2023-02-13 12:22:12 cat1 dog1
    //2023-02-13 12:02:13 cat3 dog3
    val dataFrame1 = dataFrame.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]
      .select("value").as[String]
      .flatMap(t => {
        val strs = t.split(" ") //[2023-02-13,12:02:10,cat,dog]
        val date = strs(0) + " " + strs(1)
        val animals = strs.tail.tail //[cat,dog]
        val array = animals.map((Timestamp.valueOf(date), _))//List((2023-02-13 12:02:10,cat), (2023-02-13 12:02:10,dog))
        array
      }).toDF("timestamp", "name")
      .withWatermark( "timestamp","10 minutes")//设置水印阈值为10分钟,作用在流上
      .groupBy(
        window($"timestamp", "10 minutes", "5 minutes"),
        $"name"
      ).count()

    //获取输出流
    dataFrame1.writeStream
      .outputMode(OutputMode.Update())
      .format("console")
      .option("truncate", "false")
      .start()
      .awaitTermination()
  }
}
