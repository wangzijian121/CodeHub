package com.zjyun.spark.sql.b_dataframe

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{Aggregator, Window}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, Row, SparkSession, functions}
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

  /**
   * 类型处理-布尔类型
   */
  @Test
  def sparkTypeBoolean(): Unit = {

    val df = getDfWithData
    df.where(col("id") === 2).show //判断过滤条件 过滤=2的
    df.where("id = 2 ").show //过滤=2的
    df.where("id <>2 ").show //过滤不等于2的

    df.where(col("id") > 2).where(col("id") =!= 4).show //and 多条件判断
    df.where((col("id") === 2).or(col("id") === 4)).show //or 多条件判断

    df.withColumn("is_bigger_then_2", col("id") > 2).where("is_bigger_then_2").show // 判断是否大于2的
    df.withColumn("is_bigger_then_2", expr("id >2")).where("is_bigger_then_2").show // 判断是否大于2的

  }

  /**
   * 类型处理-数值类型
   */
  @Test
  def sparkTypeNum(): Unit = {
    val df: DataFrame = List((1.111, "aaa"), (2.5, "b"), (3.888, "cc"), (4.4444, "cc")).toDF("id", "value")
    df.select(pow(col("id"), 2).alias("res")).show //取平方
    df.select(col("id") + 1).alias("res").show //+1
    df.select(col("id") - 1).alias("res").show //-1
    df.select(round(col("id"), 2)).show //保留两位小数（向上取整）
    df.select(round(col("id"), 0)).show //向下取整
    df.select(bround(col("id"), 0)).show //向下取整
  }

  /**
   * 类型处理-字符串类型
   */
  @Test
  def sparkTypeString(): Unit = {
    val df: DataFrame = List((1.111, "a wangzijian"), (2.222, "li si ")).toDF("id", "value")
    df.select(initcap(col("value"))).show //首字母大写
    df.select(lower(col("value"))).show //全部转小写
    df.select(upper(col("value"))).show //全部转大写
    df.select(col("value").contains("li")).show //判断是否包含
  }

  /**
   * 日期类型
   */
  @Test
  def sparkTypeDate(): Unit = {
    val dateDF = sparkSession.range(10)
      .withColumn("today", current_date())
      .withColumn("now", current_timestamp())
    dateDF.select(date_sub(col("today"), 5)).show //减5天
    dateDF.select(date_add(col("today"), 5)).show //加5天
    dateDF.select(months_between(lit("2017-11-14"), lit("2017-07-14"))).show(1) //月度之间的间隔

    val dateFormat = "yyyy-MM-dd"
    val dateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSSS"
    sparkSession.range(1)
      .select(to_date(lit("2025-03-24"), dateFormat)).show(1) //按照格式化的时间处理时间数据

    sparkSession.range(1)
      .select(to_timestamp(lit("2025-03-24 14:15:44.0000"), dateTimeFormat)).show(1) //精确到时分秒
  }

  /**
   * 空值处理
   */
  @Test
  def sparkTypeNull(): Unit = {
    val df: DataFrame = List((1, null), (2, "aaa"), (3, "null"), (4, "bbb")).toDF("id", "value")
    df.select(coalesce(col("id"), col("value"))).show // 返回第一个非空的列

    df.selectExpr("ifnull(value,'空值')").show //1.如果为空，则取第二个值
    df.selectExpr("nullif('a','a')").show(1) //2.如果两个值相等，则返回null
    df.selectExpr("nvl(null,'aaa')").show(1) //3. 如果第一个值为null则返回第二个值，否则返回第一个
    df.selectExpr("nvl2('a','aaa','bbb')").show(1) //4. 如果第一个值不为null则返回第二个值，否则返回第三个值

    df.na.drop.show //删除带有null的行
    df.na.drop("all", Seq("id", "value")).show //删除id列带有null的行
    df.na.drop("any", Seq("id", "value")).show //删除id列带有null的行
    df.na.fill("this is null").show() //使用文本填充null值
    df.na.replace("value", Map("null" -> "0", "aaa" -> "a", "" -> "default")).show // 使用replace 替换

    df.orderBy(asc_nulls_first("value")).show //将null值放在前面 正序排列
    df.orderBy(asc_nulls_last("value")).show //将null值放在后面 正序排列
  }

  /**
   * 复杂类型-数组
   */
  @Test
  def sparkTypeArray(): Unit = {
    val df: DataFrame = List((1, "a,b,c"), (2, "b,b,b"), (3, "c,c,c")).toDF("id", "value")
    df.select(split(col("value"), ",").alias("res")).show //按逗号分割 指定列下的所有值
    df.select(size(split(col("value"), ",").alias("res"))).show //按逗号分割 获取数组长度
    df.select(array_contains(split(col("value"), ",").alias("res"), "b")).show //按逗号分割 判断数组中是否包含某值
    df.select(split(col("value"), ",").alias("res")).show
    //炸开字段中数组
    df.withColumn("split_value", split(col("value"), ","))
      .withColumn("exploded", explode(col("split_value"))).show
    val resDF = df.select(map(col("id"), col("value")).alias("res")) //将指定字段转为map类型
    resDF.selectExpr("res[1]").show //根绝key查询value
  }

  /**
   * 复杂类型-json
   */
  @Test
  def sparkTypeJson(): Unit = {

    val jsonDF = sparkSession.range(1).selectExpr(
      """
      '{"myJSONKey" : {"myJSONValue" : [1, 2, 3]}}' as jsonString""")
    jsonDF.select(
      get_json_object(col("jsonString"), "$.myJSONKey.myJSONValue"
      ).alias("res")).show //获取json中的数据
  }

  /**
   * 复杂类型-用户自定义函数
   */
  @Test
  def sparkTypeUdf(): Unit = {
    val dataFrame = sparkSession.range(5).toDF("num")

    def power3(number: Double): Double = number * number * number

    val power3Udf = udf(power3(_: Double): Double) //注册为udf

    //dataFrame.select(power3Udf(col("num"))).show//将函数应用到  DataFrameAPI 查询

    sparkSession.udf.register("p3", power3(_: Double): Double) //将自定义函数注册到spark-sql
    //使用spark-sql查询 因为这个函数是在Spark SQL注册的，并且任何Spark SQL 函数或表达式都可以在处理DataFrame时使用
    dataFrame.selectExpr("p3(num)").show
  }

  /**
   * 聚合分组
   */
  @Test
  def groupTest(): Unit = {
    val df = getDfWithData
    //println(df.count())//这是行为操作
    //df.select(count("*")).show// 求df 的行数
    //df.select(countDistinct("value")).show //先去重，然后在算出count数量
    //df.select(approx_count_distinct("value",0.1)).show //可以指定误差的去重计数
    //
    //df.select(first("value"),last("value")).show //取第一行和最后一行
    //
    //df.select(min("id"),max("id")).show//取最大、最小值
    //
    //df.select(sum("id")).show //求和
    //df.select(sumDistinct("id")).show //去重后进行求和
    //df.select(avg("id")).show //求平均值

    //df.groupBy("value").count().show//简单分组
    //df.groupBy("value").agg(count("value"),sum("id")).show //聚合后求组内count 和sum
    //df.groupBy("value").agg("value"->"count","id"->"sum").show //聚合后使用map进行组内运算`
    df.show
    //window 开窗函数  row_number 是窗口内的行号
    //df.withColumn("window", row_number() over Window.partitionBy("value").orderBy(col("id").desc)).show

  }

  /**
   * 用户自定义聚合函数-多进 1 出
   */
  @Test
  def sparkUdaf(): Unit = {
    val df = getDfWithData.select(col("id"))
    val mySum = new MySum()
    sparkSession.udf.register("mySum", functions.udaf(mySum))
    df.selectExpr("mySum(id)").show()
  }

  /**
   * join 连接
   */
  @Test
  def joinTest(): Unit = {
    val order = sparkSession.sparkContext.parallelize(Seq(
        (1, 101, 2500), (2, 102, 1110), (3, 103, 500), (4, 102, 400), (5, 999, 888)))
      .toDF("orderId", "userId", "money")

    val customer = sparkSession.sparkContext.parallelize(Seq(
        (101, "用户101"), (102, "用户102"), (103, "用户103"), (104, "用户104"),
        (105, "用户105"), (106, "用户106")))
      .toDF("userId", "name")
    //order.show
    //customer.show

    //1.内部联接-inner join
    //customer.join(order,"userId").show

    //2.笛卡儿积 MxN
    //customer.crossJoin(order).show

    //3.左外链接-left outer join
    //customer.join(order, Seq("userId"), "left").show

    //4.右外链接-right outer join
    //customer.join(order, Seq("userId"), "right").show

    //5.全外连接-full outer join
    //customer.join(order, Seq("userId"), "full").show

    //6.左半连接-left semi join
    //customer.join(order, Seq("userId"), "left_semi").show

    //7.左反连接-left anti join
    customer.join(order, Seq("userId"), "left_anti").show

  }

  @Test
  def catalogTest(): Unit = {
    val dataFrame = Seq((1, 1, 111), (2, 2, 222)).toDF("orderId", "userId", "money")
    dataFrame.createOrReplaceTempView("order")

    /**
     * +--------+---------+-----------+
     * |database|tableName|isTemporary|
     * +--------+---------+-----------+
     * |        |    order|       true|
     * +--------+---------+-----------+
     */
    sparkSession.sql("SHOW TABLES").show() // 显示当前数据库中的表

    // 获取默认数据库
    println("默认数据库:"+sparkSession.catalog.currentDatabase)

    // 列出所有数据库
    sparkSession.catalog.listDatabases().select("locationUri").show

    // 切换到指定当前数据库
    sparkSession.catalog.setCurrentDatabase("default")
    // 列出当前数据库中的表
    sparkSession.catalog.listTables().show()

    //// 注册外部托管表（元数据指向文件路径）
    sparkSession.catalog.createTable("my_catalog_table",
      "demo-ouput.parquet/xxx.parquet", "parquet")

    sparkSession.catalog.listTables().show()
    //// 删除表
    sparkSession.catalog.dropTempView("order")  // 删除临时视图

    // 列出当前数据库中的表
    sparkSession.catalog.listTables().show()

    // 注册 UDF
    sparkSession.udf.register("my_upper", (s: String) => s.toUpperCase)

    // 列出所有函数
    sparkSession.catalog.listFunctions().show()
}
  @Test
  def sparkTaskTest(): Unit = {
    val df1 = sparkSession.range(1, 1000)
    val dataFrame = df1.groupBy("id").count()
    println(dataFrame.rdd.getNumPartitions)
    dataFrame.collect
    Thread.sleep(Long.MaxValue)
  }
}

/**
 * 自定义UDAF
 */
class MySum extends Aggregator[Int, Int, Int] {
  //初始值
  override def zero: Int = 0

  //每条数据之间的相加逻辑
  override def reduce(b: Int, a: Int): Int = if (a > 2) b else b + a

  //不同executor 的合并逻辑
  override def merge(b1: Int, b2: Int): Int = b1 + b2

  //完成之后 的输出逻辑
  override def finish(reduction: Int): Int = reduction

  //缓冲编码格式
  override def bufferEncoder: Encoder[Int] = Encoders.scalaInt

  //输出编码格式
  override def outputEncoder: Encoder[Int] = Encoders.scalaInt
}

