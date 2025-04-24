package com.zjyun.spark.structured_streaming.join操作

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

import java.sql.Timestamp

/**
 * [不使用watermark的流-流]
 *
 * 数据输入：
 * 1,张三,18,2,2024-08-08 12:10:00
 * 2,李四,20,1,2024-08-08 12:10:00
 * 3,王五,22,3,2024-08-08 12:10:00
 *
 * 数据输出：
 * -------------------------------------------
 * +---+----+---+---------+-------------------+-----------+
 * |id |name|age|office_id|timestamp          |office_name|
 * +---+----+---+---------+-------------------+-----------+
 * |1  |张三|18 |2        |2024-08-08 12:10:00|程序员     |
 * +---+----+---+---------+-------------------+-----------+
 *
 * -------------------------------------------
 * Batch: 2
 * -------------------------------------------
 * +---+----+---+---------+-------------------+-----------+
 * |id |name|age|office_id|timestamp          |office_name|
 * +---+----+---+---------+-------------------+-----------+
 * |2  |李四|20 |1        |2024-08-08 12:10:00|项目经理   |
 * +---+----+---+---------+-------------------+-----------+
 *
 * -------------------------------------------
 * Batch: 3
 * -------------------------------------------
 * +---+----+---+---------+-------------------+-----------+
 * |id |name|age|office_id|timestamp          |office_name|
 * +---+----+---+---------+-------------------+-----------+
 * |3  |王五|22 |3        |2024-08-08 12:10:00|数据分析师 |
 * +---+----+---+---------+-------------------+-----------+
 */
object StreamStaticJoin {
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

    val staticDataFrame = Seq(
      (1, "程序员"),
      (2, "项目经理"),
      (3, "数据分析师")
    ).toDF("id", "office_name")
    staticDataFrame.show

    //1,张三,18,2,2024-08-08 12:10:00
    //2,李四,20,1,2024-08-08 12:10:00
    //3,王五,22,3,2024-08-08 12:10:00
    val lines1: Dataset[String] = stream1.as[String]
    val frame1 = lines1.map(
      line => {
        val items = line.split(",")
        (items(0), items(1), items(2), items(3), Timestamp.valueOf(items(4)))
      }
    ).toDF("id", "name", "age", "office_id", "timestamp")

    val outputDataframe = frame1.join(staticDataFrame, Seq("id"), "inner")

    outputDataframe.writeStream
      .format("console")
      .outputMode(OutputMode.Update())
      .option("truncate", false)
      .start()
      .awaitTermination()
  }
}
