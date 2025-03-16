package com.zjyun.spark.sql.e_sparksession

import org.apache.spark.sql.{DataFrame, SparkSession}
case class Person(name: String, age: Long)
object SparkSessionTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark SQL 案例")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    // 对于隐式转换，例如将 RDD 转换为 DataFrame
    import spark.implicits._

    val dataframe:DataFrame =spark.read.csv("spark/src/main/scala/com/zjyun/spark/rdd/datas/movies.txt")
    dataframe.show()


    val caseClassDS = Seq(Person("Andy", 32)).toDS()
    caseClassDS.show()
  }
}
