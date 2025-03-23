package com.zjyun.spark.sql.b_dataframe

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext

object DataframeSql {
  def main(args: Array[String]): Unit = {

    val sc = getLocalSparkContext("测试dataframe的SQL功能")
    val sqlSc: SQLContext = new SQLContext(sc)
    import sqlSc.implicits._

    val rdd:RDD[(String,Int)] = sc.textFile("/tmp/data1.txt").map(line => {
      val strings = line.split(",")
      (strings(0), strings(1).toInt)
    })
    val df = rdd.toDF("name","age")
    df.createTempView("people")
    val dataFrame = sqlSc.sql(
      """
        |select name,age from people where age>1;
        |""".stripMargin)
    dataFrame.show()
  }
}
