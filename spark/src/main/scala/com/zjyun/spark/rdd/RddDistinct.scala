package com.zjyun.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object RddDistinct {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD Test")
    val sparkContext = new SparkContext(sparkConf)
    val value = sparkContext.makeRDD(List(1, 2, 3, 4, 5, 5, 6, 6, 6, 6, 7, 8, 8, 8, 9))
    value.groupBy(t => t).keys.collect().foreach(x=>print(x+" "))
    println()
    value.groupBy(t => t).map(_._1).collect().foreach(x=>print(x+" "))
  }
}
