package com.zjyun.spark.rdd.转换类算子

import org.apache.spark.{SparkConf, SparkContext}

object RddFlatMap {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD Test")
    val sparkContext = new SparkContext(sparkConf)
    val value = sparkContext.makeRDD(List("张三 100分 男", "李四 99分 女"))
    value.flatMap(x => {
      val strings = x.split(" ")
      strings.tail.map((strings.head,_))
    }).foreach(s=>print(s+" ")) //(张三,100分) (张三,男) (李四,99分) (李四,女)
  }
}
