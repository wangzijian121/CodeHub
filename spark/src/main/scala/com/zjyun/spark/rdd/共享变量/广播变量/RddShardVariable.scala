package com.zjyun.spark.rdd.共享变量.广播变量

import com.zjyun.spark.utils.Utils.getLocalSparkContext

object RddShardVariable {

    private def broadcast = {
      val sc = getLocalSparkContext("广播变量")
      val arr = 10
      val arr2 = Array(1, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 9)
      val broadCastValue = sc.broadcast(arr)//广播变量
      sc.parallelize(arr2).map(_+broadCastValue.value).foreach(println)
      broadCastValue.unpersist()  // 仅从内存中移除，可重新广播
      broadCastValue.destroy() // 彻底删除
    }

  def main(args: Array[String]): Unit = {
    broadcast
  }
}