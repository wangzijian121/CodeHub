package com.zjyun.spark.rdd.转换类算子

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object RddCogroup {


  def main(args: Array[String]): Unit = {

    val sc = getLocalSparkContext("RddReduceByKey")
    val rddData1 = sc.makeRDD(Array(("wang", 10), ("lisi", 120), ("wangwu", 90)))
    val rddData2 = sc.makeRDD(Array(("wang", "男"), ("lisi", "女"), ("zhaoliu", "女")))
    /**
     * cogroup输出
     * (zhaoliu,(CompactBuffer(),CompactBuffer(女)))
     * (wangwu,(CompactBuffer(90),CompactBuffer()))
     * (wang,(CompactBuffer(10),CompactBuffer(男)))
     * (lisi,(CompactBuffer(120),CompactBuffer(女)))
     */
    val res1 = rddData1.cogroup(rddData2)
    /**
     * join输出
     * (wang,(10,男))
     * (lisi,(120,女))
     */
    val res2 = rddData1 join rddData2
    /**
     * leftOuterJoin 输出
     * (wangwu,(90,None))
     * (wang,(10,Some(男)))
     * (lisi,(120,Some(女)))
     */
    val res3 = rddData1 leftOuterJoin rddData2

    /**
     * rightOuterJoin  输出
     * (zhaoliu,(None,女))
     * (wang,(Some(10),男))
     * (lisi,(Some(120),女))
     */
    val res4 = rddData1 rightOuterJoin rddData2

    res4.collect().foreach(x => println(x + " "))
  }
}