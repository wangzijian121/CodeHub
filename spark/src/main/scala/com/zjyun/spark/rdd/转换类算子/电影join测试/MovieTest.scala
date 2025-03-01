package com.zjyun.spark.rdd.转换类算子.电影join测试

import com.zjyun.spark.utils.Utils.{getLocalSparkContext, getStandaloneSparkContext}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
 * # 需求
 * # 存在这样一个表 movies电影表
 * # movie_id movie_name movie_types
 *
 * # 存在一个评分表
 * # user_id movie_id score timestamp
 * 1,1,5.0,1234567890
 * # 腾讯视频或者是爱奇艺
 * # 看过这个电影的人喜欢什么
 *
 * # 问题1 每个用户最喜欢哪个类型的电影[按照观看量]
 * # 问题2 每个类型中评分最高的前三个电影[平均分]
 * # 问题3 给每个用户推荐最喜欢的类型的前三个电影
 */
object MovieTest {

  val sc = getLocalSparkContext("每个类型中评分最高的前三个电影")
  val moviesRDD = sc.textFile("src/main/scala/com/zjyun/spark/rdd/datas/movies.txt")
  val ratingsRDD = sc.textFile("src/main/scala/com/zjyun/spark/rdd/datas/ratings.txt")

  /**
   * 问题1 每个用户最喜欢哪个类型的电影[按照观看量]
   */
  private def userLove: RDD[(Int, String)] = {

    //处理电影类型
    val moviesTuple = moviesRDD.map(line => {
      val lineSplitArr = line.split(",")
      val types = lineSplitArr(2).split("-")
      (lineSplitArr(0), types)
    })
    //交换评分数据
    val ratingsTuple = ratingsRDD.map(line => {
      val lineSplitArr = line.split(",")
      (lineSplitArr(1), lineSplitArr(0))
    })
    //join后数据        电影ID    电影类型        用户ID
    val baseData: RDD[(String, ((Array[String], String)))] = moviesTuple join ratingsTuple

    val res = baseData
      .map(_._2.swap)
      .flatMapValues(x => x)
      .map(t => {
        ((t._1, t._2), 1)
      })
      .reduceByKey(_ + _)
      .groupBy(_._1._1)
      .mapValues(v => {
        v.map(t => (t._1._2, t._2)).toList.sortBy(-_._2).take(1)//这里可以使用比较大小的方式求max
      }).map(t => (t._1.toInt, t._2(0)._1))
    println("====用户喜欢的电影类型===-")
    res.collect().foreach(x => println(x))
    res
  }

  /**
   * 问题2: 每个类型中评分最高的前三个电影
   */
  def scoreTop3: RDD[(String, List[(String, String)])] = {
    val moviesTuple = moviesRDD.map(line => {
      val lineSplitArr = line.split(",")
      val types = lineSplitArr(2).split("-")
      ((lineSplitArr(0), lineSplitArr(1)), types.toList)
    }).flatMapValues(x => x).map(t => (t._1._1, (t._1._2, t._2)))

    val ratingsTuple = ratingsRDD.map(line => {
      val lineSplitArr = line.split(",")
      (lineSplitArr(1), lineSplitArr(2))
    })

    val baseJoin = moviesTuple join ratingsTuple

    val res = baseJoin.map(x => {
        (x._2._1._2, (x._2._1._1, x._2._2))
      }).groupByKey()
      .mapValues(v =>
        v.toList.sortBy(_._2)(Ordering.String.reverse))
    println("====各种类型的电影评分===-")
    res.collect().foreach(x => println(x))
    res
  }

  private def userPush = {
    val userLoveRDD = userLove.map(_.swap)
    val scoreTop3RDD = scoreTop3

    println("====向用户推荐的电影===-")
    val baseJoin= userLoveRDD join  scoreTop3RDD
    val res = baseJoin.map(t=>{
      (t._2._1,t._2._2.take(3))
    })

    res.collect().foreach(x => println(x))
    sc.stop()
  }

  def main(args: Array[String]): Unit = {
    userPush
  }
}
