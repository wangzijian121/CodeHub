package com.zjyun.spark.rdd.转换类算子

import org.apache.spark.{SparkConf, SparkContext}

object RddReduceByKey {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)
    //名字和成绩
    /*val arr = Array(("小张", 22), ("小张", 25), ("小盖",16), ("小盖",19))
    sc.makeRDD(arr).reduceByKey(_+_).collect().foreach(println)*/

    val arr2 = Array(1, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 9)
    sc.makeRDD(arr2)
      .map((_, null))
      .reduceByKey((x, y) => null)
      .map(_._1).collect().foreach(x=>print(x+" "))
  }
}