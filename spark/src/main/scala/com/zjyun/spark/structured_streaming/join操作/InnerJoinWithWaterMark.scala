package com.zjyun.spark.structured_streaming.join操作

import org.apache.spark.sql.functions.expr
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

import java.sql.Timestamp

/**
 * 流-流使用watermark的内连接join
 */
object InnerJoinWithWaterMark {
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
    //1,张三,18,2024-08-08 12:10:00
    //2,李四,20,2024-08-08 12:20:00
    //3,王五,22,2024-08-08 12:30:00
    //1,张三,18,2024-08-08 13:20:00

    //1,程序员,2024-08-08 12:09:00
    //1,程序员,2024-08-08 13:20:00
    //2,项目经理,2024-08-08 12:30:00
    //3,数据分析师1,2024-08-08 12:40:00
    //3,数据分析师,2024-08-08 13:40:00
    val lines1: Dataset[String] = stream1.as[String]
    val persons = lines1.map(
      line => {
        val items = line.split(",")
        (items(0), items(1), items(2),Timestamp.valueOf(items(3)))
      }
    ).toDF("user_id", "name", "age", "time1")
      .withWatermark("time1", "5 minutes")

    val lines2: Dataset[String] = stream2.as[String]
    val offices = lines2.map(
      line => {
        val items = line.split(",")
        (items(0), items(1),Timestamp.valueOf(items(2)))
      }
    ).toDF("office_id", "office_name","time2").withWatermark("time2", "5 minutes")

    val outputDataframe = persons.join(offices,expr(
      """
        |    user_id = office_id AND
        |    time1 >= time2 AND
        |    time1 <= time2 + interval 1 hour
        |""".stripMargin), "inner")// can be "inner", "leftOuter", "rightOuter", "fullOuter", "leftSemi"

    outputDataframe.writeStream
      .format("console")
      .outputMode(OutputMode.Append())//只支持append模式
      .option("truncate", false)
      .start()
      .awaitTermination()
  }
}
