package com.zjyun.spark.utils

case object Utils {
  def printType[T](value: T): Unit = {
    println(s"内容: $value, 类型: ${value.getClass.getName}")
  }
}
