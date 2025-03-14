package com.zjyun.spark.utils

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.{SqlSession, SqlSessionFactory, SqlSessionFactoryBuilder}
import java.io.{InputStream, Reader}

case object Utils {
  def printType[T](value: T): Unit = {
    println(s"内容: $value, 类型: ${value.getClass.getName}")
  }

  def getLocalSparkContext(name: String): SparkContext = {
    val conf = new SparkConf()
      .setAppName(name)
      .setMaster("local[*]")
    new SparkContext(conf)
  }

  def getStandaloneSparkContext(name: String) = {
    System.setProperty("hadoop.home.dir", "D:\\winutils")
    val conf = new SparkConf().setMaster("spark://hadoop01:7077")
      .setAppName(name)
    //.setJars(Array())
    //conf.setMaster("local[*]")
    new SparkContext(conf)
  }

  def getLocalSparkSession(name: String): SparkSession = {
    SparkSession.builder()
      .appName(name)
      .master("local[*]")
      .getOrCreate()
  }
}
