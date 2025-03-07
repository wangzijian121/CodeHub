package com.zjyun.spark.rdd.转换类算子

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.{SparkConf, SparkContext}

object RddReduceByKey {


  def main(args: Array[String]): Unit = {

    val sc = getLocalSparkContext("RddReduceByKey")
    //名字和成绩
    val arr = Array(("小张", 22), ("小张", 25), ("小盖", 16), ("小盖", 19))
    sc.makeRDD(arr,3).reduceByKey(_ + _,3).map(t => {
      Thread.sleep(Long.MaxValue)
    }).collect().foreach(println)

    //val arr2 = Array(1, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 9)
    //sc.makeRDD(arr)
    //  .map((_, null))
    //  .reduceByKey((x, y) => null)
    //  .map(_._1).collect().foreach(x=>print(x+" "))
  }
}