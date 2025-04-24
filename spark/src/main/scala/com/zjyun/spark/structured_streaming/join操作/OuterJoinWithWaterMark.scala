package com.zjyun.spark.structured_streaming.join操作

import org.apache.spark.sql.functions.expr
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

import java.sql.Timestamp

/**
 * 流-流使用watermark的外连接join
 * 外连接join时只有当超过watermark阈值的数据才会输出
 * +-------+-----+---+-------------------+---------+-----------+-----+
 * |user_id|name |age|time1              |office_id|office_name|time2|
 * +-------+-----+---+-------------------+---------+-----------+-----+
 * |1      |张三1|18 |2024-08-08 12:10:00|null     |null       |null |
 * |2      |张三2|18 |2024-08-08 12:20:00|null     |null       |null |
 * +-------+-----+---+-------------------+---------+-----------+-----+
 */
object OuterJoinWithWaterMark {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("使用流式join【不使用watermark】")
      .master("local[2]").getOrCreate()
    import sparkSession.implicits._
    val stream1: DataFrame = sparkSession.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 6666)
      .load()

    val stream2: DataFrame = sparkSession.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 7777)
      .load()
    //输入数据
    //1,张三1,18,2024-08-08 12:10:00
    //2,张三2,18,2024-08-08 12:20:00
    //1,张三3,18,2024-08-08 12:30:00
    //1,张三4,18,2024-08-08 12:40:00


    //1,程序员1,2024-08-08 12:10:00
    //1,程序员2,2024-08-08 12:20:00
    //1,程序员3,2024-08-08 12:30:00
    //1,程序员4,2024-08-08 12:40:00
    val lines1: Dataset[String] = stream1.as[String]
    val persons = lines1.map(
        line => {
          val items = line.split(",")
          (items(0), items(1), items(2), Timestamp.valueOf(items(3)))
        }
      ).toDF("user_id", "name", "age", "time1")
      .withWatermark("time1", "5 minutes")

    val lines2: Dataset[String] = stream2.as[String]
    val offices = lines2.map(
      line => {
        val items = line.split(",")
        (items(0), items(1), Timestamp.valueOf(items(2)))
      }
    ).toDF("office_id", "office_name", "time2").withWatermark("time2", "5 minutes")

    val outputDataframe = persons.join(offices, expr(
      """
        |    user_id = office_id AND
        |    time1 >= time2 AND
        |    time1 <= time2 + interval 1 hour
        |""".stripMargin), "leftOuter") // can be "inner", "leftOuter", "rightOuter", "fullOuter", "leftSemi"

    outputDataframe.writeStream
      .format("console")
      .outputMode(OutputMode.Append()) //只支持append模式
      .option("truncate", false)
      .start()
      .awaitTermination()
  }
}
