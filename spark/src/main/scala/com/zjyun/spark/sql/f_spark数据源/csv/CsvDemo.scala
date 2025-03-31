package com.zjyun.spark.sql.f_spark数据源.csv

import com.zjyun.spark.utils.Utils.getLocalSparkSession
import org.apache.spark.sql.SparkSession

case class Person(name: String, age: Int, sex: String)

object CsvDemo {
  def main(args: Array[String]): Unit = {
    val basePath = "src/main/scala/com/zjyun/spark/sql/f_spark数据源/csv/"
    val sparkSession = SparkSession.builder()
      .appName("csv 数据源测试")
      .master("local[*]")
      .getOrCreate()

    val columnNames = Seq("name", "age", "sex")
    val dataFrame = sparkSession.read
      .format("csv")
      .option("pathGlobFilter", "*2.csv")//过滤文件
      .option("header", true) //csv有表头，有表头的话去掉表头（name,age,sex）
      .schema("name STRING,age INT,sex STRING")//指定字段类型
      .load(basePath)
    //  .toDF("name","age","sex")//没有表头时转为指定字段的DF
    //.toDF(columnNames: _*)
    dataFrame.show
    /*    dataFrame.createOrReplaceTempView("person")

        //过滤100岁以下的正常人类 (0-0)!
        val res = sparkSession.sql(
          """
            |select * from  person where age <100
            |""".stripMargin)
        res.show()
        // 保存为自定义文件名
        res.coalesce(1) // 合并为一个分区，避免生成多个文件
          .write
          .option("header", "true") // 添加表头
          .mode("overwrite")
          .csv(basePath + "/output/output.csv")*/
  }
}
