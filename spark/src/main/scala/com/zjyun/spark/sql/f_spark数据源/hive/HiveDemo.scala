package com.zjyun.spark.sql.f_spark数据源.hive

import com.zjyun.spark.utils.Utils.getLocalSparkSession
import org.apache.spark.sql.SparkSession

import java.io.File

object HiveDemo {
  def main(args: Array[String]): Unit = {
    // warehouseLocation 指向托管数据库和表的默认位置
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath
    System.setProperty("HADOOP_USER_NAME", "root")
    val sparkSession = SparkSession.builder()
      .appName("hive demo")
      .master("local[*]")
      .config("spark.sql.catalogImplementation", "hive")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .getOrCreate()
    import sparkSession.sql
    //sql("CREATE TABLE IF NOT EXISTS db.loves (student_id int, value string) ")
    //sql("insert into  db.loves values(111,'美术')")
    val stu = sql("select * from db.stu")
    stu.show()
    stu.createOrReplaceTempView("stu")

    val love = sql("select * from db.loves")
    love.show
    love.createOrReplaceTempView("stu")

    val joinDf = stu.join(love, stu("id") === love("student_id"), "left")
      .select(stu("id"), stu("name"), stu("date"), love("value"))
    joinDf.show
  }
}
