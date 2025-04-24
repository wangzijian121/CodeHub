package com.zjyun.spark.structured_streaming.支持的sink

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.spark.sql.functions.{current_timestamp, date_format}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}

import java.io.{BufferedWriter, OutputStreamWriter, PrintWriter}
import java.text.SimpleDateFormat
import java.util.Date


/**
 * Kafka输出
 */
object ToHdfs {
  var fs: FileSystem = _
  var stream: FSDataOutputStream = _
  var pw: PrintWriter = _
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("Kafka输出").master("local[*]").getOrCreate()
    import sparkSession.implicits._
    val dataFrame = sparkSession.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 8888)
      .load()
    val dfDt=dataFrame.withColumn("dt", date_format(current_timestamp(), "yyyy-MM-dd"))
    dfDt.repartition(3).writeStream
      .partitionBy("dt")
      .format("text")
      .option("path", "hdfs://mycluster/home/wangzijian/spark/structured_streaming/")
      .option("checkpointLocation", "hdfs://mycluster/home/wangzijian/checkpoint")
      .outputMode(OutputMode.Append())
      .trigger(Trigger.ProcessingTime("5 seconds"))
      .start()
      .awaitTermination()
  }
}