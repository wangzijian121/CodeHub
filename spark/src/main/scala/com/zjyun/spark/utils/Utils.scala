package com.zjyun.spark.utils

import org.apache.spark.{SparkConf, SparkContext}

case object Utils {
  def printType[T](value: T): Unit = {
    println(s"内容: $value, 类型: ${value.getClass.getName}")
  }

  def initSc(name: String) = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName(name)
    new SparkContext(conf)
  }
}
