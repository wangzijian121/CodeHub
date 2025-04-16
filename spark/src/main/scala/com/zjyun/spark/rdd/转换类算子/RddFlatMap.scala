package com.zjyun.spark.rdd.转换类算子

import org.apache.spark.{SparkConf, SparkContext}

object RddFlatMap {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD Test")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.makeRDD(Array("a 1 2", "b 3 4")).map(_.split(" "))
      .foreach(t=>println(t))
    sparkContext.makeRDD(Array("a 1 2", "b 3 4")).flatMap(_.split(" "))
      .foreach(t=>print(t))
    //val value = sparkContext.makeRDD(List("张三 100分 男", "李四 99分 女"))
    //value.flatMap(x => {
    //  val strings = x.split(" ")
    //  val tuples = strings.tail.map((_, strings.head))
    //  println(tuples.toList)
    //  tuples
    //}).foreach(s=>println(s+" ")) //(张三,100分) (张三,男) (李四,99分) (李四,女)
  }
}
