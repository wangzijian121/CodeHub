package com.zjyun.spark.rdd.转换类算子

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object RddKeysAndValues {
  def main(args: Array[String]): Unit = {
    val sc = getLocalSparkContext("RddKeysAndValues")
    val res = sc.makeRDD(Array(("a", 1), ("b", 2), ("c", 3)))
    res.keys.collect().foreach(x => print(x + " "))//a b c
    res.values.collect().foreach(x => print(x + " "))//1 2 3
  }
}
