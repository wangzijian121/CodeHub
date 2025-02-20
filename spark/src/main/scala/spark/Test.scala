package spark

import org.apache.log4j.{Logger, PropertyConfigurator}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import spark.FileUtils.str2FileUtils

object Test {

  private def fun2(inputPath: String, outputPath: String) = {
    //System.setProperty("hadoop.home.dir", "D:\\winutils")
    val sparkConf = new SparkConf()
    //sparkConf.setMaster("local")
    sparkConf.setAppName("word count!")
    val sp = new SparkContext()
    outputPath.deletePath
    sp.textFile(inputPath).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile(outputPath)
    sp.stop()
  }

  def main(args: Array[String]): Unit = {
    @transient lazy val log = Logger.getLogger(this.getClass)
    val Array(inputPath, outputPath) = Array(args(0), args(1))
    fun2(inputPath, outputPath)
  }
}