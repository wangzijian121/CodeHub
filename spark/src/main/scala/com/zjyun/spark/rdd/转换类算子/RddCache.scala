package com.zjyun.spark.rdd.转换类算子

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.storage.StorageLevel

object RddCache {
  private def withoutCache = {
    val sc = getLocalSparkContext("spark缓存")

    val list = List("hello spark", "hello scala")
    val rdd = sc.makeRDD(list)
    val flatRDD = rdd.flatMap(_.split(" "))
    val mapRDD = flatRDD.map(
      word => {
        // 为了证明 RDD 对象被重用时，会重新读取数据，打印标识（四个core打印四行）
        println("@@@@@@@@@@@")
        (word, 1)
      })
    // 第一次使用 mapRDD
    val reduceRDD = mapRDD.reduceByKey(_ + _)
    reduceRDD.collect().foreach(println)
    println("********************************")
    // 第二次使用 mapRDD
    val groupRDD = mapRDD.groupByKey()
    groupRDD.collect().foreach(println)
    sc.stop()
  }

  private def useCache = {
    val sc = getLocalSparkContext("spark缓存")

    val list = List("hello spark", "hello scala")
    val rdd = sc.makeRDD(list)
    val flatRDD = rdd.flatMap(_.split(" "))
    val mapRDD = flatRDD.map(
      word => {
        // 为了证明 RDD 对象被重用时，会重新读取数据，打印标识（四个core打印四行）
        println("@@@@@@@@@@@")
        (word, 1)
      })
    mapRDD.cache()//1.第一种方式，使用cache
    //mapRDD.persist(StorageLevel.MEMORY_ONLY)

    // 第一次使用 mapRDD
    val reduceRDD = mapRDD.reduceByKey(_ + _)
    reduceRDD.collect().foreach(println)
    println("********************************")
    // 第二次使用 mapRDD
    val groupRDD = mapRDD.groupByKey()
    groupRDD.collect().foreach(println)
    sc.stop()
  }

  private def useCheckPoint = {
    val sc = getLocalSparkContext("checkpoint")
// 因为checkpoint 需要落盘，需要指定checkpoint存放的位置
    sc.setCheckpointDir("D:\\checkpoint")

    val list = List("hello spark", "hello scala")
    val rdd = sc.makeRDD(list)
    val flatRDD = rdd.flatMap(_.split(" "))
    val mapRDD = flatRDD.map(
      word => {
        // 为了证明 RDD 对象被重用时，会重新读取数据，打印标识（四个core打印四行）
        println("@@@@@@@@@@@")
        (word, 1)
      })
    // 使用 checkpoint 持久化中间数据
    mapRDD.checkpoint()


    val reduceRDD = mapRDD.reduceByKey(_ + _)// 第一次使用 mapRDD
    reduceRDD.collect().foreach(println)
    println("********************************")

    val groupRDD = mapRDD.groupByKey()// 第二次使用 mapRDD
    groupRDD.collect().foreach(println)
    sc.stop()
  }

  def main(args: Array[String]): Unit = {
    //withoutCache
    //useCache
    useCheckPoint
  }
}
