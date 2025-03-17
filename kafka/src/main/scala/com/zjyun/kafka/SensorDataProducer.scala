package com.zjyun.kafka

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties
import java.util.concurrent.{Executors, TimeUnit}
import scala.util.Random

object SensorDataProducer {
  def main(args: Array[String]): Unit = {
    // Kafka配置
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.98:9092")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    val topic = "streams-plaintext-input"
    val random = new Random()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // 创建一个定时执行的线程池
    val executor = Executors.newScheduledThreadPool(1)

    // 生成传感器ID的辅助方法
    def generateSensorId(): String = {
      //2^4 =16个测点
      val areas = Array("30M", "31M")
      val types = Array("AB", "CD")
      val numbers = (1 to 2).map(n => f"$n%02d")
      val suffixes = Array("CT", "PT")

      val area = areas(random.nextInt(areas.length))
      val typeCode = types(random.nextInt(types.length))
      val number = numbers(random.nextInt(numbers.length))
      val suffix = suffixes(random.nextInt(suffixes.length))

      s"${area}${typeCode}${number}${suffix}001TEP2.OUT"
    }

    // 定义回调
    val callback = new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        if (exception != null) {
          println(s"Error sending message: ${exception.getMessage}")
        }
      }
    }

    // 定义数据生成任务
    val task = new Runnable {
      def run(): Unit = {
        try {
          // 每次执行生成5条数据
          for (_ <- 1 to 5) {
            val sensorId = generateSensorId()
            val value = 20 + random.nextDouble() * 60 // 生成20-80之间的随机值
            val timestamp = LocalDateTime.now().format(dateFormatter)
            val status = random.nextInt(2) // 生成0或1的状态码

            val data = f"$sensorId,$value%.2f,$timestamp,$status"
            val record = new ProducerRecord[String, String](topic, sensorId, data)

            producer.send(record, (metadata: RecordMetadata, exception: Exception) => {
              if (exception != null) {
                println(s"Error sending message: ${exception.getMessage}")
              } else {
                println(s"Produced: $data -> topic: ${metadata.topic()}, " +
                  s"partition: ${metadata.partition()}, " +
                  s"offset: ${metadata.offset()}, " +
                  s"timestamp: ${metadata.timestamp()}")
              }
            })
          }
          producer.flush()
        } catch {
          case e: Exception =>
            println(s"Error producing message: ${e.getMessage}")
            e.printStackTrace()
        }
      }
    }

    // 每秒执行一次任务
    executor.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS)

    // 添加关闭hook
    Runtime.getRuntime.addShutdownHook(new Thread(() => {
      println("Shutting down producer...")
      executor.shutdown()
      try {
        if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
          println("Executor terminated successfully")
        } else {
          println("Executor termination timeout")
        }
      } finally {
        producer.close(java.time.Duration.ofSeconds(5))
        println("Producer closed")
      }
    }))

    // 保持程序运行
    Thread.sleep(Long.MaxValue)
  }
} 