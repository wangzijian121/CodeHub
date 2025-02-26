package com.zjyun.spark.rdd.转换类算子

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object RddMapValues {
  def main(args: Array[String]): Unit = {
    val sc = getLocalSparkContext("RddMapValues")
    val res = sc.makeRDD(Array(("a", 1), ("b", 1), ("c", 1)))
      .mapValues(_ + 2)
    res.collect().foreach(x => print(x + " "))
  }
}
