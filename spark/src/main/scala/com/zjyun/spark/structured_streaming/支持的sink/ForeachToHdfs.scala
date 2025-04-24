package com.zjyun.spark.structured_streaming.支持的sink

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}

import java.io.{BufferedWriter, OutputStreamWriter, PrintWriter}
import java.text.SimpleDateFormat
import java.util.{Date, UUID}


/**
 * foreach
 * TODO 当多分区时，实时写入hdfs会有丢数的问题，单分区就不会出现，可能与socket有关
 *
 */
object ForeachToHdfs {
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

    dataFrame.writeStream
      .foreach(new ForeachWriter[Row] {
        override def open(partitionId: Long, epochId: Long): Boolean = {
          fs = FileSystem.get(new Configuration)
          val dff = new SimpleDateFormat("yyyy-MM-dd")
          val datePath = dff.format(new Date)
          val path = new Path("hdfs://mycluster/home/wangzijian/spark/structured_streaming/"
            + datePath + "/part-" + partitionId + "/" + UUID.randomUUID()+".txt")
          //stream = if (!fs.exists(path)) fs.create(path) else fs.append(path)
          stream=fs.create(path)
          pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)))
          true
        }

        override def process(value: Row): Unit = {
          println(value.getAs("value").toString)
          pw.println(value.getAs("value").toString)
        }

        override def close(errorOrNull: Throwable): Unit = {
          if (pw != null) pw.close()
          stream.close()
        }
      })
      .outputMode(OutputMode.Append())
      .trigger(Trigger.ProcessingTime("5 seconds"))
      .start()
      .awaitTermination()
  }
}