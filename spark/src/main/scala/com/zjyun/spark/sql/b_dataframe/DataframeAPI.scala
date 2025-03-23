package com.zjyun.spark.sql.b_dataframe

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions.{col, column, expr, lit}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.junit.Test

case class Student(id: Int, value: String) extends Serializable

class DataframeAPI {
  val sparkSession = SparkSession.builder()
    .appName("测试dataframe API功能")
    .master("local[*]")
    .getOrCreate()


  import sparkSession.implicits._

  /**
   * 测试数据集
   *
   * @return 测试数据集 DataFrame
   */
  def getDfWithData: DataFrame = List((1, "aaa"), (2, "b"), (3, "cc"), (4, "cc")).toDF("id", "value")

  /**
   * 模式
   */
  def schemaTest = {
    val testDf = getDfWithData
    testDf.printSchema()
    val schema: StructType = testDf.schema
    println(schema)
  }

  /**
   * 列操作
   */
  def colTest = {
    import org.apache.spark.sql.functions.expr
    getDfWithData.show
    println(getDfWithData.columns.toList) //访问所有列
    println(getDfWithData.col("id").toString()) //访问指定列
    getDfWithData.orderBy(expr("length(value)")).show() //按照value列的长度排序
  }

  /**
   * 行操作
   */
  def lineTest = {
    //输出第一行
    val firstRow: Row = getDfWithData.first()
    println(firstRow)
    //创建行
    val row = Row(4, "d")
    println(row)
  }

  /**
   * 1.通过schema+Rdd[Row]手动创建Dataframe
   *
   */
  @Test
  def createDataFrameWithSchema = {
    //创建RDD并转换为RDD[Row]
    val rdd: RDD[(Int, String)] = sparkSession.sparkContext
      .parallelize(Seq((1, "aaa"), (2, "b"), (3, "cc")))
    val rddToRow: RDD[Row] = rdd.map(line => Row(line._1, line._2))
    //创建schema
    val schema: StructType = StructType(Array(
      StructField("id", IntegerType),
      StructField("value", StringType)
    ))
    // schema+RDD[Row]创建Dataframe
    val dataFrame = sparkSession.createDataFrame(rddToRow, schema)
    dataFrame.show()
  }

  /**
   * 2.通过toDF() 创建Dataframe
   */
  @Test
  def createDataFrameWithToDF = {
    //创建RDD
    val rdd: RDD[(Int, String)] = sparkSession.sparkContext
      .parallelize(Seq((1, "aaa"), (2, "b"), (3, "cc")))
    //转为Dataframe
    rdd.toDF("id", "value").show()
    //通过seq传入参数(道理一样)
    val seq = Seq("id", "value")
    rdd.toDF(seq: _*).show()
  }

  /**
   * 3.通过Object对象创建Dataframe
   */
  @Test
  def createDataFrameWithObject = {
    //创建测试RDD
    val rdd: RDD[(Int, String)] = sparkSession.sparkContext
      .parallelize(Seq((1, "aaa"), (2, "b"), (3, "cc")))
    val rddStudent = rdd.map { case (id, value) => Student(id, value)
    }
    //不用指定列参数
    rddStudent.toDF().show()
  }

  /**
   * 列操作
   */
  @Test
  def columnFunc(): Unit = {
    val df = getDfWithData
    df.select("id").show() //查询列
    df.select(df.col("id")).show //查询列
    df.select(col("id")).show //查询列
    df.select(column("id")).show //查询列
    df.select('id).show //查询列
    df.select($"id").show //查询列
    df.select(expr("id")).show //查询列
    df.select("id", "value").show() //查询多列

    df.withColumn("new_col", lit(1)).show //创建列
    df.withColumn("new_col", expr("id >=2")).show //创建列

    df.withColumnRenamed("value", "value_rename").show() //重命名列

    df.drop("value").show //删除指定列

    println(df.withColumn("id", col("id").cast("string")).schema) //修改指定列的类型

    df.select(expr("id as rename_id")).show() //重命名列

    df.selectExpr("id as rename_id_use_selectExpr").show() //通过SelectExpr 查询ID后并重命名列
    df.selectExpr("*", "id >=2 as id_check").show() //通过SelectExpr 表达式，判断ID列是否大于2，并添加到原始字段后面
    df.selectExpr("avg(id)").show() //通过SelectExpr 查询ID列的平均值
    df.select(lit("这里是平均值->").as("平均值"), expr("avg(id)")).show() //通过SelectExpr 为平均值添加字面量
  }

  /**
   * 行操作
   */
  @Test
  def lineFunc(): Unit = {
    val df = getDfWithData
    df.where("id>=2").show //过滤行
    df.filter("id>=2").show //过滤行
    df.selectExpr("id", " value").distinct().show() //去重行
    df.selectExpr("value").distinct().show() //去重行
    df.sample(true, 0.5).show() //随机抽样
    df.limit(1).show //limit

    //连接和追加行
    val dfNew = df.selectExpr("id * 2 as id", "concat(value, '_new') as value")
    df.union(dfNew).show()

    df.orderBy("id").show() //行排序
    df.orderBy(col("id").desc).show() //行排序-倒序

    println(df.rdd.getNumPartitions) //获取分区数
    val newRddPartition = df.repartition(2, col("id")) //按照ID划分成2个分区
    println(newRddPartition.rdd.getNumPartitions) //获取分区数
    df.collect().foreach(println) //driver 端获取df
  }
}

