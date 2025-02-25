package com.zjyun.spark.rdd

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}

import java.io.PrintWriter

object RddMapPartitionsWithIndex {


    def main(args: Array[String]): Unit = {
      val conf = new SparkConf()
      conf.setAppName("test")
      conf.setMaster("local[*]")
      val sc = new SparkContext(conf)
      val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
      val sink_dir = "D:/test"
      val fs1 = FileSystem.get(new Configuration())
      if (fs1.exists(new Path(sink_dir)))
        throw new Exception("output path already exists!!!")
      else
        fs1.mkdirs(new Path(sink_dir))
      fs1.close()

      sc.makeRDD(arr, 3).mapPartitionsWithIndex((index, it) => {
        val fs = FileSystem.get(new Configuration())
        val out = fs.create(new Path(sink_dir + "/part-0000" + index))
        val pw = new PrintWriter(out, true)
        it.foreach(line => pw.println(line))
        pw.close()
        out.close()
        fs.close()
        Iterator.empty
      }).collect().foreach(print)
    }
}