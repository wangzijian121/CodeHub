package com.zjyun.spark.streaming.b_spark_streaming写入到hdfs

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.streaming.{Seconds, StreamingContext}

import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date

/**
 * 需求： 读取socket中的数据，计数写到hdfs上
 * 1.使用coalesce 合并分区，否则每秒生成25个分区，造成大量小文件。
 * 2.按日期-小时分文件夹，追加数据到文件中。
 */
object HdfsWriter {
  def main(args: Array[String]): Unit = {
    val parent_path = "/spark_sink/"
    val file_name = "t.txt"
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("hdfs writer")
    val streamingContext: StreamingContext
    = new StreamingContext(conf = conf, batchDuration = Seconds(5))

    val lines = streamingContext.socketTextStream("localhost", 6666)
    lines.foreachRDD((rdd, time) => {
      rdd.flatMap(_.split(" "))
        .map((_, 1))
        .reduceByKey(_ + _)
        .coalesce(5)
        .mapPartitionsWithIndex((index, value) => {
          val fs = FileSystem.get(new Configuration())
          val dateStr = new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date())

          // 拼接完整路径
          val allPath = s"$parent_path$dateStr/$index-$file_name"
          val path = new Path(allPath)

          val stream: FSDataOutputStream = if (fs.exists(path)) fs.append(path) else fs.create(path)
          val pw = new PrintWriter(stream, true)
          value.foreach { case (key, value) =>
            pw.println(s"$key->$value")
          }
          pw.close()
          fs.close()
          Iterator.empty
        }).foreach((t: String) => ())
    })
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
