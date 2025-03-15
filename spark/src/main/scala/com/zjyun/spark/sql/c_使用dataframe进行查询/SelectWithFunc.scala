package com.zjyun.spark.sql.c_使用dataframe进行查询

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext

object SelectWithFunc {
  def main(args: Array[String]): Unit = {

    val sc = getLocalSparkContext("测试dataframe")
    val sqlSc: SQLContext = new SQLContext(sc)
    import sqlSc.implicits._

    val rdd: RDD[(String, Int)] = sc.textFile("/tmp/data1.txt").map(line => {
      val strings = line.split(",")
      (strings(0), strings(1).toInt)
    })
    val df = rdd.toDF("name", "age")
    df.createTempView("people")
    df.select("*")
      .where("age>1")
      .orderBy($"age".desc) //使用desc进行排序
      .show()
  }
}

