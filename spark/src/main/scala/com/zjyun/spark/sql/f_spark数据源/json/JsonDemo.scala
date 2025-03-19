package com.zjyun.spark.sql.f_spark数据源.json

import com.zjyun.spark.utils.Utils.getLocalSparkSession

case class Person(name: String, age: Int, love: List[String])

object JsonDemo {
  def main(args: Array[String]): Unit = {
    val basePath = "spark/src/main/scala/com/zjyun/spark/sql/f_spark数据源/json/"
    val sparkSession = getLocalSparkSession("json demo")

    // 方法2：使用选项配置读取JSON文件
    println("=== 方法2：使用选项配置读取JSON文件 ===")
    val jsonDF  = sparkSession.read
      .option("multiline", "true") // 支持多行JSON
      .json(basePath + "json-demo-input.json")
    
    jsonDF.show()

    jsonDF.printSchema()
    jsonDF.createOrReplaceTempView("json_table")
    val res = sparkSession.sql(
      """
        |select age+1 as age,love,name,other from json_table
        |""".stripMargin)
    res.show()
    res.write.json(basePath + "json-demo-output.json")
  }
}
