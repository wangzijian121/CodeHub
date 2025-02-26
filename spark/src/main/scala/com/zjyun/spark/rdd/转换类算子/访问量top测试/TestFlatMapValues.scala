package com.zjyun.spark.rdd.转换类算子.访问量top测试

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object TestFlatMapValues {
  def main(args: Array[String]): Unit = {
    val sc = getLocalSparkContext("TestFlatMapValues")
    val data = sc.makeRDD(Array("chinese-zhangsan,lisi,wangwu", "math-zhangsan,zhaosi", "english-lisi,wangwu,zhaosi"))
    //求出每个老师教授的课程都有什么？("zhangsan",("chinese","math","lisi'))
    val res = data.map(item => {
      val arr = item.split("-")
      (arr(0), arr(1))
    }).flatMapValues(_.split(","))
      .map(_.swap)
      .groupByKey()
      .map(t => (t._1, t._2.mkString(",")))
    res.collect().foreach(println)
  }
}
