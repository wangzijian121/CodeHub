package com.zjyun.spark.rdd.转换类算子

import org.apache.spark.{SparkConf, SparkContext}

object RddGroupByAndGroupByKey {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)
    val arr = Array(("小张", "男", 22), ("小红", "女", 25), ("小盖", "女", 16), ("小张", "男", 19))

    //使用group by 按成绩排序
    sc.makeRDD(arr).groupBy(_._2).collect().foreach(println)

    //使用groupByKey 按成绩排序
    sc.makeRDD(arr).map(item=>{
      (item._2,(item._1,item._3))
    }).groupByKey().collect().foreach(println)

    //使用groupBy和 groupByKey 去重
    val arr2 = Array(1, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 9)
    sc.makeRDD(arr2).map((_,null)).groupBy(_._1).map(_._1).collect().foreach(x=>print(x+" "))
    println()
    sc.makeRDD(arr2).map((_,null)).groupByKey().map(_._1).collect().foreach(x=>print(x+" "))

  }
}