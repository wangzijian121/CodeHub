package com.zjyun.spark.rdd.转换类算子

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object RddSortByAndAndSortByKey {
  def main(args: Array[String]): Unit = {
    val sc = getLocalSparkContext("RddSortByAndAndSortByKey")
    val res = sc.makeRDD(Array(("k", 3), ("a", 1), ("i", 0)))
      //.sortByKey()
      .sortByKey(false)
      .sortBy(_._2)
      //.sortBy(- _._2)
    res.collect().foreach(x => print(x + " "))
  }
}
