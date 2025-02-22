package com.zjyun.spark.wordcount

import com.zjyun.spark.wordcount.FileUtils.str2FileUtils
import org.apache.log4j.Logger
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  private def fun2(inputPath: String, outputPath: String) = {
    //System.setProperty("hadoop.home.dir", "D:\\winutils")
    val sparkConf = new SparkConf()
    //sparkConf.setMaster("local")
    sparkConf.setAppName("word count!")
    val sp = new SparkContext()
    outputPath.deletePath
    sp.textFile(inputPath).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile(outputPath)
    sp.stop()
  }

  def main(args: Array[String]): Unit = {
    @transient lazy val log = Logger.getLogger(this.getClass)
    val Array(inputPath, outputPath) = Array(args(0), args(1))
    fun2(inputPath, outputPath)
  }
}