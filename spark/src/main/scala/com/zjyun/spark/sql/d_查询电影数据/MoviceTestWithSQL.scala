package com.zjyun.spark.sql.d_查询电影数据

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.spark.sql.{DataFrame, SQLContext}

object MoviceTestWithSQL {

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
    ratings.foreach(println)
    val ratingsDf: DataFrame = ratings.toDF("user_id", "movie_id", "score")

    moviesDf.createTempView("movies")
    ratingsDf.createTempView("ratings")

    /*    val resDataFrame = sqlSc.sql(
          """
            |SELECT window_res.user_id, window_res.movie_type
            |FROM (
            |	SELECT *, row_number() OVER (PARTITION BY user_id ORDER BY score DESC) AS rn
            |	FROM (
            |		SELECT r.user_id, r.movie_id, m.movie_type, r.score
            |		FROM movies m
            |			JOIN ratings r ON r.movie_id = m.movie_id
            |		ORDER BY user_id
            |	) res
            |) window_res
            |WHERE rn = 1
            |""".stripMargin)*/

    // 第一步：连接 movies 与 ratings 表，并按照 user_id 排序
    val joinDF = sqlSc.sql(
      """
        |SELECT r.user_id, r.movie_id, m.movie_type, r.score
        |FROM movies m
        |JOIN ratings r ON r.movie_id = m.movie_id
        |ORDER BY user_id
        |""".stripMargin)
    joinDF.createOrReplaceTempView("joinView")
    joinDF.show()

    // 第二步：在 joinView 上使用窗口函数，计算每个用户中 score 排序后的行号
    val windowDF = sqlSc.sql(
      """
        |SELECT *, row_number() OVER (PARTITION BY user_id ORDER BY score DESC) AS rn
        |FROM joinView
        |""".stripMargin)
    windowDF.createOrReplaceTempView("windowView")
    windowDF.show

    // 第三步：从 windowView 中筛选 rn = 1 的记录，即每个用户分数最高的电影
    val resDataFrame = sqlSc.sql(
      """
        |SELECT user_id, movie_type
        |FROM windowView
        |WHERE rn = 1
        |""".stripMargin)

    resDataFrame.show()

    /*    +-------+-----join关联查询---+----------+-----+
        |user_id|movie_id|movie_type|score|
        +-------+--------+----------+-----+
        |      1|       3|      灾难|  2.5|
          |      1|       1|      犯罪|  5.0|
          |      1|       3|      爱情|  2.5|
          |      1|       1|      剧情|  5.0|
          |      2|       1|      犯罪|  1.0|
          |      2|       1|      剧情|  1.0|
          |      2|       2|      剧情|  4.5|
          |      3|       1|      犯罪|  2.5|
          |      3|       6|      科幻|  6.0|
          |      3|       1|      剧情|  2.5|
          |      3|       6|      动作|  6.0|
          |      3|       6|      悬疑|  6.0|
          |      4|       5|      动作|  4.0|
          |      4|       5|      冒险|  4.0|
          |      4|       5|      科幻|  4.0|
          |      4|       6|      科幻|  4.5|
          |      4|       6|      动作|  4.5|
          |      4|       6|      悬疑|  4.5|
          |      5|       5|      科幻|  8.0|
          |      5|       7|      剧情|  6.0|
          +-------+--------+----------+-----+*/
    /*    +-------+--------+--开窗编上序号------+-----+---+
        |user_id|movie_id|movie_type|score| rn|
        +-------+--------+----------+-----+---+
        |      1|       1|      剧情|  5.0|  1|
          |      1|       1|      犯罪|  5.0|  2|
          |      1|       3|      爱情|  2.5|  3|
          |      1|       3|      灾难|  2.5|  4|
          |      2|       2|      剧情|  4.5|  1|
          |      2|       1|      剧情|  1.0|  2|
          |      2|       1|      犯罪|  1.0|  3|
          |      3|       6|      科幻|  6.0|  1|
          |      3|       6|      动作|  6.0|  2|
          |      3|       6|      悬疑|  6.0|  3|
          |      3|       1|      剧情|  2.5|  4|
          |      3|       1|      犯罪|  2.5|  5|
          |      4|       6|      科幻|  4.5|  1|
          |      4|       6|      动作|  4.5|  2|
          |      4|       6|      悬疑|  4.5|  3|
          |      4|       5|      动作|  4.0|  4|
          |      4|       5|      科幻|  4.0|  5|
          |      4|       5|      冒险|  4.0|  6|
          |      5|       5|      动作|  8.0|  1|
          |      5|       5|      科幻|  8.0|  2|
          +-------+--------+----------+-----+---+*/

    /*+-------+--按编号过滤得出结果--------+
    |user_id|movie_type|
    +-------+----------+
    |      1|      剧情|
      |      2|      剧情|
      |      3|      科幻|
      |      4|      科幻|
      |      5|      动作|
      +-------+----------+*/
  }

  def main(args: Array[String]): Unit = {
    fun1
  }
}
