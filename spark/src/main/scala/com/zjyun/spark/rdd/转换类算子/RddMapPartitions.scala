package com.zjyun.spark.rdd.转换类算子

import org.apache.spark.{SparkConf, SparkContext}

import java.sql.DriverManager
import scala.collection.mutable.ListBuffer

object RddMapPartitions {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD Test")
    val sc = new SparkContext(sparkConf)
    val value = sc.makeRDD(List(1, 2, 3, 4, 5, 5, 6, 6, 6, 6, 7, 8, 8, 8, 9), 3)//这里分了3个区，就会将数据分成3份批量处理

    //value.map(_*2).collect.foreach(x=>print(x+" ")) //使用map
    //println()
    //value.mapPartitions(item=>item.map(_*2)).collect.foreach(x=>print(x+" ")) // 使用mapPartitions
    selectDb(sc)
  }

  /**
   * 按照ID查询数据库
   *
   * @return
   */
  private def selectDb(sc: SparkContext) = {
    val ids = sc.makeRDD(List("d003", "d004", "d005", "d006", "d007", "d008"), 2)
    ids.mapPartitions(part => {
      val connection = DriverManager.getConnection("jdbc:mysql://192.168.56.99:3306/employees", "root", "root")
      println("创建connection！")
      val statement = connection.prepareStatement("select * from employees.departments where dept_no=? ")
      var buffer = ListBuffer[(String, String)]()
      //这里不可以使用map,因为map返回的是RDD,是惰性求值
      part.foreach(id => {
        statement.setString(1, id)
        val resultSet = statement.executeQuery()
        var name = ""
        while (resultSet.next()) {
          name = resultSet.getString("dept_name")
        }
        buffer.append((id, name))
      })
      statement.close()
      connection.close()
      buffer.toIterator
    }
    ).collect().foreach(println)
  }
}