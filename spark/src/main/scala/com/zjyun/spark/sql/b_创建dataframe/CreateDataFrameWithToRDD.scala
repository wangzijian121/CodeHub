package com.zjyun.spark.sql.b_创建dataframe

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext

object CreateDataFrameWithToRDD {
  def main(args: Array[String]): Unit = {

    val sc = getLocalSparkContext("测试dataframe")
    val sqlSc: SQLContext = new SQLContext(sc)
    import sqlSc.implicits._

    val rdd:RDD[(String,Int)] = sc.textFile("/tmp/data.txt").map(line => {
      val strings = line.split(",")
      (strings(0), strings(1).toInt)
    })
    val df = rdd.toDF("name","age")
    df.show()
  }
}
