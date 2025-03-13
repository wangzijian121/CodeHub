package com.zjyun.spark.sql.b_dataframe

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.{SparkConf, SparkContext}

object DataFrameDemo {
  def main(args: Array[String]): Unit = {

    val sc: SparkContext = getLocalSparkContext("测试dataframe")
    sc.textFile("/tmp/data.txt").foreach(println)
  }
}
