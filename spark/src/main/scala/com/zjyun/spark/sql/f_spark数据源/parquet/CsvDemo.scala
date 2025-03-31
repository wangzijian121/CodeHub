package com.zjyun.spark.sql.f_spark数据源.parquet

import com.zjyun.spark.utils.Utils.getLocalSparkSession
import org.apache.spark.sql.{SaveMode, SparkSession}

case class Person(name: String, age: Int, sex: String)

object ParquetDemo {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("csv 数据源测试")
      .master("local[*]")
      .getOrCreate()

    val path = "src/main/scala/com/zjyun/spark/sql/f_spark数据源/parquet/demo-input.csv"

    //生成测试文件
    val dataFrame = sparkSession.read
      .format("csv")
      .option("header", true) //csv有表头，有表头的话去掉表头（name,age,sex）
      .schema("name STRING,age INT,sex STRING")//指定字段类型
      .load(path)
    dataFrame.write
      .format("parquet")
      .mode(SaveMode.ErrorIfExists)
      .save("src/main/scala/com/zjyun/spark/sql/f_spark数据源/parquet/demo-ouput.parquet")

    //读取parquet文本
    sparkSession.read
      .format("parquet")
      .load("src/main/scala/com/zjyun/spark/sql/f_spark数据源/parquet/demo-ouput.parquet/*.parquet")
      .toDF().createOrReplaceTempView("parquet_table")

    sparkSession.sql("select * from parquet_table where age<100").show

  }
}
