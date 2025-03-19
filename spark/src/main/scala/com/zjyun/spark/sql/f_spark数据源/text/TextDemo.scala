package com.zjyun.spark.sql.f_spark数据源.text

import com.zjyun.spark.utils.Utils.getLocalSparkSession

case class Person(name: String, age: Int, sex: String)

object CsvDemo {
  def main(args: Array[String]): Unit = {
    val basePath = "spark/src/main/scala/com/zjyun/spark/sql/f_spark数据源/text/"
    val sparkSession = getLocalSparkSession("csv demo")

    val columnNames = Seq("value")
    val dataFrame = sparkSession.read
      .text(basePath + "text-demo-input.txt")
      .toDF(columnNames: _*)

    dataFrame.createOrReplaceTempView("person")

    dataFrame.show
    //过滤100岁以下的正常人类 (0-0)!
    val res = sparkSession.sql(
      """
        |select * from  person where value ='aaa'
        |""".stripMargin)
    res.show
    // 保存为自定义文件名
    res.write
      .mode("overwrite")
      .csv(basePath + "/output/output.txt")
  }
}
