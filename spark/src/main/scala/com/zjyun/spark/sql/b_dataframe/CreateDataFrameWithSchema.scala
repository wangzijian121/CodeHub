package com.zjyun.spark.sql.b_dataframe

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}

/**
 * rdd + schema 的形式创建DataFrame
 */
object CreateDataFrameWithSchema {
  def main(args: Array[String]): Unit = {
    val sc = getLocalSparkContext("测试dataframe")
    val sqlSc: SQLContext = new SQLContext(sc)

    val rdd= sc.textFile("/tmp/data.txt").map(line => {
      val strings = line.split(",")
      Row(strings(0), strings(1).toInt)
    })
    val schema =StructType(Array(
      StructField("",StringType),
      StructField("",IntegerType)
    ))
    val dataFrame = sqlSc.createDataFrame(rdd, schema)
    dataFrame.show()
  }
}

case  class Student(name:String,id:Int)