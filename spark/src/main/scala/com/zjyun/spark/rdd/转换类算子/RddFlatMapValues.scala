package com.zjyun.spark.rdd.转换类算子

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object RddFlatMapValues {
  def main(args: Array[String]): Unit = {
    val sc = getLocalSparkContext("RddMapValues")
    val res = sc.makeRDD(Array(("a", "1 2"), ("b", "3 4"), ("c", "5 6")))
      .flatMapValues(_.split(" "))
    res.collect().foreach(x => print(x + " "))
  }
}
