package com.zjyun.spark.rdd.转换类算子.访问量top测试

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object TestTop {

  //首先求出【整个文档】中【老师访问量】最高的top3

  /**
   * (laoyang,53)
   * (xiaohe,30)
   * (laoliu,21)
   * (laochen,15)
   * (unclewang,7)
   * (laozhang,6)
   */
  def top3 = {
    val sc = getLocalSparkContext("top3")
    val value = sc.textFile("src/main/scala/com/zjyun/spark/rdd/datas/urls.txt")
    //(xiaohe,7) xiaohe 作为key
    val res = value
      .map(_.split(",")(1))
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(-_._2)
      .take(3)
    res.foreach(x => println(x + " "))
  }

  //每个【专业】中【访问量】最高的top2
  def top2 = {

    val sc = getLocalSparkContext("top2")
    val value = sc.textFile("src/main/scala/com/zjyun/spark/rdd/datas/urls.txt")
    val res = value
      .map(line => {
        val strings = line.split(",")
        ((strings(0), strings(1)), 1)
      })
      .reduceByKey(_ + _)                               //((java,laochen),15)
      .map(item => (item._1._1, (item._1._2, item._2)))//(java,(laochen,15))
      .groupByKey()       //(java,CompactBuffer((laochen,15), (laoliu,21), (laozhang,6), (xiaohe,11)))
      .mapValues(
        item => item.toList.sortBy(-_._2)//按Value中的指定位数排序
      )

    res.collect().foreach(x => println(x + " "))
  }

  def main(args: Array[String]): Unit = {
    top2
    top3
  }
}
