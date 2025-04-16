package com.zjyun.spark.structured_streaming.数据源.text

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * 读取目录下的text文件，当新增文件时输出新加文件内容
 * +---+----+
 * | id|   c|
 * +---+----+
 * |  4|越南|
 * |  5|英国|
 * |  6|德国|
 * +---+----+
 */
object TextStreaming {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local[2]")
      .appName("structured_streaming读取text")
      .getOrCreate()

    //获取输入流
    val dataFrame: DataFrame = sparkSession.readStream
      .format("text")
      .option("path", "/home/wangzijian/datasource/spark/text/")
      .load()

    import sparkSession.implicits._
    //处理中间dataframe
    //datafream 读取text默认是数组，需要转为String，可以通过dataset转，或者使用getString
    //val dataset: Dataset[String] = dataFrame.as[String]

    val tableIdName = dataFrame.map(line => {
        val strs = line.getString(0).split(",")
        (strs(0), strs(1))
      }).toDF("id", "country")
      .selectExpr("id", "country as c")
      .createOrReplaceTempView("tableA")

    //使用sparkSQL查询
    val resDf = sparkSession.sql(
      """
        |select * from  tableA where id >3;
        |""".stripMargin)

    //获取输出流
    resDf.writeStream
      .format("console")
      .start()
      .awaitTermination()
  }
}
