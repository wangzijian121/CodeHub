package com.zjyun.spark.sql.d_查询电影数据

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, SQLContext}

object MoviceTestWithAPI {

  /**
   * 查询计算 「每个用户」最喜欢的「电影类型」top1
   * 每个用户的最高评分类型
   */
  def fun1 = {
    val sc = getLocalSparkContext("spark-sql电影数据计算")
    val sqlSc: SQLContext = new SQLContext(sc)

    val moviesRDD = sc.textFile("spark/src/main/scala/com/zjyun/spark/rdd/datas/movies.txt")
    val ratingsRDD = sc.textFile("spark/src/main/scala/com/zjyun/spark/rdd/datas/ratings.txt")
    import sqlSc.implicits._

    val moviesDf: DataFrame = moviesRDD.map(line => {
        val strs = line.split(",")
        val movieId = strs.head
        val types: String = strs(2)
        (movieId, types)
      })
      .flatMap(x => {
        //(7,动作-犯罪-剧情) => (7,动作)(7,犯罪)(7,剧情)
        val str = x._2.split("-")
        str.map((x._1, _))
      })
      .toDF("movie_id", "movie_type")


    val ratings = ratingsRDD.map(line => {
      val items = line.split(",")
      (items(0), items(1), items(2))
    })
    val ratingsDf: DataFrame = ratings.toDF("user_id", "movie_id", "score")

    import org.apache.spark.sql.functions._
    moviesDf.join(ratingsDf, "movie_id").orderBy("user_id")
      .withColumn("rn", row_number() over Window.partitionBy("user_id").orderBy($"score".desc))
      .filter("rn=1")
      .show()
    /*+--------+----------+-------+-----+---+
    |movie_id|movie_type|user_id|score| rn|
    +--------+----------+-------+-----+---+
    |       1|      剧情|      1|  5.0|  1|
      |       2|      剧情|      2|  4.5|  1|
      |       6|      科幻|      3|  6.0|  1|
      |       6|      科幻|      4|  4.5|  1|
      |       5|      动作|      5|  8.0|  1|
      +--------+----------+-------+-----+---+*/

  }

  def main(args: Array[String]): Unit = {
    fun1
  }
}
