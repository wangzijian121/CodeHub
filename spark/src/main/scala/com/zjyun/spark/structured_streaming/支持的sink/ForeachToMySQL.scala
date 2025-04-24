package com.zjyun.spark.structured_streaming.支持的sink

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}
import org.apache.spark.sql.streaming.Trigger

object ForeachToMySQL {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SocketToMySQL")
      .master("local[*]")
      // 请确保 MySQL JDBC driver 在 classpath 中：mysql-connector-java-*.jar
      .getOrCreate()

    import spark.implicits._

    // 1. 从 socket 读取流
    val streamingDF = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 8888)
      .load()
      .toDF("value") // 列名叫 value

    // 2. 自定义 ForeachWriter，写入 MySQL
    val writer = new ForeachWriter[Row] {
      var conn: Connection = _
      var stmt: PreparedStatement = _

      // 每个 partition 的每个 epoch 调用一次 open
      override def open(partitionId: Long, epochId: Long): Boolean = {
        // 加载驱动（可选）
        Class.forName("com.mysql.cj.jdbc.Driver")
        // 建立连接（注意 URL 中要带上时区、字符集等参数）
        conn = DriverManager.getConnection(
          "jdbc:mysql://192.168.56.107:3306/spark?useSSL=false&serverTimezone=UTC&characterEncoding=utf8",
          "root", "root"
        )
        // 预编译 SQL
        stmt = conn.prepareStatement("INSERT INTO test(value) VALUES(?)")
        true
      }

      // 对于每一行调用一次 process
      override def process(row: Row): Unit = {
        val v = row.getAs[String]("value")
        stmt.setString(1, v)
        stmt.executeUpdate()
      }

      // 当一个 partition 的该 epoch 结束（成功或失败）时调用
      override def close(error: Throwable): Unit = {
        if (stmt != null) stmt.close()
        if (conn != null) conn.close()
      }
    }

    // 3. 启动写流
    val query = streamingDF.writeStream
      .foreach(writer)                // 用 ForeachWriter
      .outputMode("append")           // 按行追加
      .trigger(Trigger.ProcessingTime("5 seconds"))
      .start()
    query.awaitTermination()
  }
}
