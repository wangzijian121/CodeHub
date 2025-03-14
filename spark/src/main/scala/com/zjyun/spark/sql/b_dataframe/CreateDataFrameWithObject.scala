package com.zjyun.spark.sql.b_dataframe

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.sql.SQLContext

object CreateDataFrameWithObject {
  def main(args: Array[String]): Unit = {

    val sc = getLocalSparkContext("测试dataframe")
    val sqlSc: SQLContext = new SQLContext(sc)
    import sqlSc.implicits._

    val rdd= sc.textFile("/tmp/data.txt").map(line => {
      val strings = line.split(",")
      Student(strings(0), strings(1).toInt)
    })
    val df = rdd.toDF()
    df.show()
  }
}

