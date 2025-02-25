package com.zjyun.spark.rdd.行动类算子

import org.apache.spark.{SparkConf, SparkContext}

object RddReduce {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)
    val arr = Array(("小张", "男"), ("小红", "女"), ("小盖", "女"), ("小张", "男"))
    val arr2 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    //使用 reduceBy
    //sc.makeRDD(arr).reduce(_._1)
    val i = sc.makeRDD(arr2).reduce(_ + _)
    println(i)
  }
}