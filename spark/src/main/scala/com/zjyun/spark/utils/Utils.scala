package com.zjyun.spark.utils

import org.apache.spark.{SparkConf, SparkContext}

case object Utils {
  def printType[T](value: T): Unit = {
    println(s"内容: $value, 类型: ${value.getClass.getName}")
  }

  def getLocalSparkContext(name: String) = {
    val conf = new SparkConf()
      .setAppName(name)
      .setMaster("local[*]")
    new SparkContext(conf)
  }

  def getStandaloneSparkContext(name: String) = {
    System.setProperty("hadoop.home.dir", "D:\\winutils")
    val conf = new SparkConf().setMaster("spark://hadoop01:7077")
      .setAppName(name)
      //.setJars(Array())
    //conf.setMaster("local[*]")
    new SparkContext(conf)
  }
}
