package com.zjyun.spark.rdd.shuffle

import org.apache.spark.sql.SparkSession

object sparkShuffleSpill {
  def haveSpill(): Unit = {
    val spark = SparkSession.builder()
      .appName("Shuffle Memory vs Disk")
      .master("local[*]") // 在本地运行
      .config("spark.shuffle.spill", true) // 允许溢写磁盘
      .config("spark.memory.fraction", "0.1") // 限制可用内存，让其容易溢写
      .getOrCreate()

    val sc = spark.sparkContext

    // 生成一个大数据集 (1千万条数据)
    val data = sc.parallelize(1 to 10000000,1).map(x => (x % 1000, x))

    // 触发 Shuffle 操作 (groupByKey)
    val grouped = data.groupByKey(1)

    // 计算
    grouped.count()

    // 让 Spark UI 保持运行以观察 Shuffle Spill 统计信息
    Thread.sleep(600000)

    spark.stop()
  }

  /**
   * 单分区没有shuffle 操作
   */
  def noSpill = {
    val spark = SparkSession.builder()
      .appName("Shuffle In-Memory Example")
      .master("local[*]") // 本地运行
      .config("spark.shuffle.spill", true) // 允许溢写（但不会发生）
      .config("spark.memory.fraction", "0.6") // 增加 Shuffle 内存占比
      .getOrCreate()

    val sc = spark.sparkContext

    // 生成小规模数据集 (100万条数据)
    val data = sc.parallelize(1 to 100000,1).map(x => (x % 100, x))

    // 使用 reduceByKey 而不是 groupByKey，减少内存占用
    val reduced = data.reduceByKey(_ + _,1)

    // 触发计算
    reduced.count()

    // 让 Spark UI 保持运行以观察 Shuffle Spill
    Thread.sleep(600000)

    spark.stop()
  }

  def main(args: Array[String]): Unit = {

    haveSpill
    //noSpill
  }
}