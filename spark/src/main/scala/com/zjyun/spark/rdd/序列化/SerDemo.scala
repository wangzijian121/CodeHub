package com.zjyun.spark.rdd.序列化

import com.zjyun.spark.utils.Utils.getLocalSparkContext
import org.apache.hadoop.io.Text
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SerDemo {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("SerDemo")
      .setMaster("local[*]")
    //配置spark 启用Kryo 序列化
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer") //classOf[KryoSerializer].getName 一样效果
    // 要求主动注册
    conf.set("spark.kryo.registrationRequired", "true")
    // 方案1：
    //val classes: Array[Class[_]] = Array[Class[_]](
    //  classOf[UserInfo],
    //  classOf[Text],
    //  Class.forName("scala.reflect.ClassTag$GenericClassTag"),
    //  classOf[Array[UserInfo]]
    //)
    //将上面的类注册
    //conf.registerKryoClasses(classes)

    //方案2 使用自定义注册器：
    conf.set("spark.kryo.registrator",classOf[MyRegistrator].getName)
    val sc = new SparkContext(conf)

    val rdd: RDD[String] = sc.parallelize(List("aa", "aa", "bb", "aa"), 2)
    val broad: Broadcast[UserInfo] = sc.broadcast(new UserInfo)
    val pairRdd: RDD[(String, UserInfo)] = rdd.map(f => {
      val userInfo: UserInfo = broad.value
      (f, userInfo)
    })
    // 因为groupByKey有shuffle，需要序列化
    pairRdd.groupByKey().collect().foreach(println)
  }
}