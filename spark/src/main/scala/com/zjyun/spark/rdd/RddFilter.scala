package com.zjyun.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

object RddFilter {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD Test")
    val sparkContext = new SparkContext(sparkConf)
    val value = sparkContext.makeRDD(List("张三 100 男", "李四 99 女"))
    value.filter(x => x.split(" ")(1).toInt == 100).collect().foreach(x=>print(x+" "))//过滤分数为100的。输出：张三 100 男
  }
}
