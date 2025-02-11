package spark

import org.apache.log4j.{Logger, PropertyConfigurator}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Test {


  private def fun1() = {
    System.setProperty("hadoop.home.dir", "D:\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("word count")
    val sp = new SparkContext(conf)
    val lineRdd: RDD[String] = sp.textFile("datas")//hello spark
    val wordRdd: RDD[String] = lineRdd.flatMap(_.split(" "))
    //(hello,1)(scala,1)(scala,1)
    val wordMap: RDD[(String, Int)] = wordRdd.map((_, 1))
    //(scala,CompactBuffer((scala,1), (scala,1)))
    //(scala,CompactBuffer((scala,1), (scala,1)))
    //(hello,CompactBuffer((hello,1), (hello,1), (hello,1), (hello,1)))
    //(spark,CompactBuffer((spark,1), (spark,1)))
    val wordGroup: RDD[(String, Iterable[(String, Int)])] = wordMap.groupBy(t => t._1)
    val reduce = wordGroup.map({
      case (word, list) => (word, list.size)
    })
    reduce.foreach(println)
    sp.stop()
  }

  private def fun2() = {
    System.setProperty("hadoop.home.dir", "D:\\winutils")
    val sp = new SparkContext(new SparkConf().setMaster("local").setAppName("word count"))
    sp.textFile("datas").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).foreach(println)
    sp.stop()
  }

  def main(args: Array[String]): Unit = {
    @transient lazy val log = Logger.getLogger(this.getClass)
    //fun1()
    log.info("测试INFO")
    log.error("测试ERROR")
    fun2()
  }
}