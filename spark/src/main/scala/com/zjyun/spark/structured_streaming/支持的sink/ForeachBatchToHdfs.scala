package com.zjyun.spark.structured_streaming.支持的sink

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.{DataFrame, Dataset, ForeachWriter, Row, SparkSession}

import java.io.{BufferedWriter, OutputStreamWriter, PrintWriter}
import java.text.SimpleDateFormat
import java.util.Date


/**
 * spark structured streaming-foreachBatch
 */
object ForeachBatchToHdfs {
  var fs: FileSystem = _
  var stream: FSDataOutputStream = _
  var pw: PrintWriter = _

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("Kafka输出").master("local[*]").getOrCreate()
    val dataFrame = sparkSession.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 8888)
      .load()

    dataFrame.repartition(3).writeStream
      .foreachBatch((batchDF: DataFrame, batchId: Long) => {
        //缓存
        batchDF.persist()
        //保存到HDFS
        batchDF.write.format("text")
          .option("path", "hdfs://mycluster/home/wangzijian/spark/structured_streaming/"+batchId)
          .option("checkpointLocation", "hdfs://mycluster/home/wangzijian/checkpoint/batch1")
          .mode("append")
          .save()
        //保存到本地
        batchDF.write.format("csv")
          .option("path", "hdfs://mycluster/home/wangzijian/spark/structured_streaming/"+batchId)
          .option("checkpointLocation", "hdfs://mycluster/home/wangzijian/checkpoint/batch2")
          .mode("append")
          .save()
        //取消缓存
        batchDF.unpersist()
        ()
      })
      .outputMode(OutputMode.Append())
      .trigger(Trigger.ProcessingTime("3 seconds"))
      .start()
      .awaitTermination()
  }
}