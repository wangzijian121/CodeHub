package com.zjyun.spark.rdd.转换类算子.自定义分区器

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.Partitioner

class MyPartitioner(numsPartition: Int) extends Partitioner {

  override def numPartitions: Int = numsPartition

  override def getPartition(key: Any): Int = {
    //定义分区规则
    key match {
      case x:Int if x < 5 => 0
      case x:Int if x > 5 && x < 10 => 1
      case _ => 2
    }
  }
}

object CustomerPartitioner {
  def main(args: Array[String]): Unit = {
    val array = Array(
      (1, "laoyang"), (2, "laoliu"), (3, "laochen"),(4, "laoyang"), (5, "laoliu"), (6, "laochen"),
      (7, "laoyang"), (8, "laoliu"), (9, "laochen"),(10, "laoyang"), (11, "laoliu"), (12, "laochen")
    )

    val sc = getLocalSparkContext("自定义分区器")
    val rdd = sc.makeRDD(array).sortByKey()
    val res = rdd.partitionBy(new MyPartitioner(3)).mapPartitionsWithIndex((index, iter) => {
      iter.map(item => (index, item))
    })
    res.collect().foreach(println)
    sc.stop()
  }
}
