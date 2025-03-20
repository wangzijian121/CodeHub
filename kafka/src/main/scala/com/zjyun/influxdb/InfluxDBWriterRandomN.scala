package com.zjyun.influxdb

import java.time.Instant
import java.util.concurrent.{Executors, TimeUnit}
import com.influxdb.client.InfluxDBClientFactory
import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.write.Point

import scala.collection.mutable._
import scala.util.Random

object InfluxDBDemo {
  def main(args: Array[String]): Unit = {
    // InfluxDB 配置
    val influxDBUrl = "http://192.168.8.204:8086" // 修改为你的 InfluxDB 访问地址
    val token = "8uwRKIG3_tk-4XDhj6KYxA7F2KuR__Ocns8xlztN8gvepieMc8gmdOE8LBrIeMJC3VeOBqLPkKXdrrrIcsmTig==" // 修改为你的 InfluxDB Token
    val org = "cosmo" // 修改为你的组织名称
    val bucket = "b1" // 桶名称

    // 创建 InfluxDB 客户端
    val influxDBClient = InfluxDBClientFactory.create(influxDBUrl, token.toCharArray, org)
    // 获取阻塞写入 API
    val writeApi = influxDBClient.getWriteApiBlocking

    // 创建一个调度线程池，每秒执行一次数据写入任务
    val scheduler = Executors.newScheduledThreadPool(1)

    val sensors = Seq(
      ("W3.UNIT1.10AGCDCSAO08", "全厂有功总和", 300.00, 400.00, 10.00),
      ("W3.UNIT1.10CKB01XB109", "有功功率", 300.00, 400.00, 10.00),
      ("W3.UNIT1.10AGCDCSAO06", "发电机DCS有功", 300.00, 400.00, 10.00),
      ("W3.UNIT1.10AGCDCSAO04", "发电机有功上升速率", 1.00, 20.00, 1.00),
      ("W3.UNIT1.10HLA10CT001", "送风机A入口温度", 4.00, 10.00, 1.00),
      ("W3.UNIT1.10HLA10CT005", "送风机A出口温度", 9.00, 10.00, 1.00),
      ("W3.UNIT1.10HLA10CP201", "送风机A出口压力", 1.00, 2.00, 1.00)
    )
    // 定义数据写入任务
    val task = new Runnable {
      override def run(): Unit = {

        // 统一使用当前时间戳
        val timestamp = Instant.now()
        val cacheMap: HashMap[String, Double] = HashMap()

         //遍历所有传感器写入数据
        //每次选其中N个
         val randomSeq = Random.shuffle(sensors).take(4 + Random.nextInt(4))
        println("生成测点数："+randomSeq.length)
        randomSeq.foreach { case (sensorId, sensorType, min: Double, max: Double, step: Double) =>

          //取缓存值
          val cacheValue = cacheMap.getOrElseUpdate(sensorId, (max + min) / 2)
          //随机值
          val randomValue = Random.nextDouble() * step
          //随机加减
          val randomAddSub = if (Random.nextBoolean) randomValue else -randomValue
          //添加后判断上下限
          val tryValue = cacheValue + randomAddSub
          val resValue: Double = if (tryValue > max) max else if (tryValue < min) min else tryValue
          cacheMap.put(sensorId, resValue)
          // 构造数据点：measurement 统一为 "plant_data"，标签区分 sensor 和 type
          val point = Point
            .measurement("sensor_data")
            .addTag("sensor", sensorId)
            .addTag("type", sensorType)
            .addField("value", resValue)
            .time(timestamp, WritePrecision.MS)
          try {
            writeApi.writePoint(bucket, org, point)
            println(s"写入数据 -> sensor: $sensorId, type: $sensorType, value: $resValue")
          } catch {
            case e: Exception => println(s"写入数据时出错: ${e.getMessage}")
          }
        }
      }
    }

    // 每1秒执行一次任务
    scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS)

    // 添加关闭钩子，程序退出时关闭客户端和调度器
    sys.addShutdownHook {
      scheduler.shutdown()
      influxDBClient.close()
      println("InfluxDB 客户端和调度器已关闭。")
    }
  }
}
