package com.zjyun.spark.rdd.共享变量.累加器

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object RddShardVariable {

  /**
   * 使用rdd外全局变量测试
   */
  def getItemCount = {
    val sc = getLocalSparkContext("RddShardVariable-getItemCount")
    val arr = Array(1, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 9)
    var count = 0
    sc.makeRDD(arr, 3)//3分区
      .map(t => {
        count = count + 1
        t
      })
      .mapPartitionsWithIndex((index, value) => {
        value.map(("分区:" + index, _))
      }).groupByKey().foreach(println)
    println("累加计数：" + count)// 输出 0，并没有进行累加
  }

  /**
   * 使用累加器
   */
  def accumulator = {
    val sc = getLocalSparkContext("RddShardVariable-getItemCount")
    val arr = Array(1, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 9)
    val count = sc.longAccumulator("累加计数")
    sc.makeRDD(arr, 3)//3分区
      .map(t => {
        count.add(1)
        t
      })
      .mapPartitionsWithIndex((index, value) => {
        value.map(("分区:" + index, _))
      }).groupByKey().foreach(println)
    println("累加计数：" + count.value)// 输出 0，并没有进行累加
  }

  def useCheckpoint = {
    val sc = getLocalSparkContext("RddShardVariable-getItemCount")
    val arr = Array(1, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 9)
    val count = sc.longAccumulator("kl")
    sc.makeRDD(arr, 3)//3分区
      .map(t => {
        count.add(1)
        t
      })
      .mapPartitionsWithIndex((index, value) => {
        value.map(("分区:" + index, _))
      }).groupByKey().foreach(println)
    println("累加计数：" + count.value)// 输出 0，并没有进行累加
  }

  def main(args: Array[String]): Unit = {
    //getItemCount
    //accumulator

  }
}