package com.zjyun.spark.structured_streaming.管理流信息

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.StreamingQueryListener
import org.apache.spark.sql.streaming.StreamingQueryListener.{QueryProgressEvent, QueryStartedEvent, QueryTerminatedEvent}

/**
 * 各种触发器
 */
object ManageStream {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("触发器")
      .master("local[*]")
      // 请确保 MySQL JDBC driver 在 classpath 中：mysql-connector-java-*.jar
      .getOrCreate()


    spark.streams.addListener(new StreamingQueryListener() {
      override def onQueryStarted(queryStarted: QueryStartedEvent): Unit = {
        println("Query started: " + queryStarted.id)
      }
      override def onQueryTerminated(queryTerminated: QueryTerminatedEvent): Unit = {
        println("Query terminated: " + queryTerminated.id)
      }
      override def onQueryProgress(queryProgress: QueryProgressEvent): Unit = {
        println("Query made progress: " + queryProgress.progress)
      }
    })

    // 1. 从 socket 读取流
    val streamingDF = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 8888)
      .load()

    // 3. 启动写流
    val query = streamingDF.writeStream
      .format("console")
      .outputMode("append") // 按行追加
      .start()
/*    println("查询ID："+query.id)
    println("查询runId：" +query.runId )
    println("查询名：" +query.name )
    println("流状态：" +query.status)
    println(query.lastProgress)
    query.explain()
    println("当前活动流："+spark.streams.active)*/
    query.awaitTermination()
  }
}
