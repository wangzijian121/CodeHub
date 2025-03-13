package com.zjyun.spark.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.kstream._
import org.apache.kafka.streams.state.WindowStore
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}

import java.lang.{Double, Long}
import java.time.Duration
import java.util.Properties
import java.util.concurrent.CountDownLatch

object SensorDataStreamProcessorSum {
  def main(args: Array[String]): Unit = {
    val inputTopic = "streams-plaintext-input"
    val outputTopic = "streams-plaintext-output"

    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "SensorDataStreamProcessor")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.98:9092")
    props.putIfAbsent(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.putIfAbsent(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")

    val builder = new StreamsBuilder()

    // 读取数据流
    val textLines: KStream[String, String] = builder.stream(inputTopic)

    val parsedStream: KStream[String, Double] = textLines.mapValues(v => {
      println("原始数据：" + v)
      var res = 0.0
      if (v != "" && v.contains(",")) {
        val double = v.split(",")(1).toDouble
        res = double
      }
      res
    })
    val groupedByKey: KGroupedStream[String, Double] = parsedStream.groupByKey()

    val materialized: Materialized[String, Double, WindowStore[Bytes, Array[Byte]]] =
      Materialized.as("aggregated-store")
        .withKeySerde(Serdes.String())
        .withValueSerde(Serdes.Double())

    val window: TimeWindowedKStream[String, Double] = groupedByKey
      .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(10)))

    val aggregater: KTable[Windowed[String], Double] = window aggregate(
      () => new Double(0.0d),
      (key: String, newValue: Double, aggValue: Double) => {
        Double.valueOf(aggValue + newValue)
      },
      materialized
    )
    aggregater.toStream.foreach((key, value) => {
      println(
        s"""
           |窗口key: ${key.key()}
           |窗口时间: ${key.window().startTime()} ~ ${key.window().endTime()}
           |聚合值: $value
           |""".stripMargin)
    })

    //    aggregater.toStream.to(outputTopic)

    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
    val latch: CountDownLatch = new CountDownLatch(1)

    // 清理本地状态
    streams.cleanUp()

    // 添加状态监听器
    streams.setStateListener((newState, oldState) => {
      println(s"Streams状态从 $oldState 变更为 $newState")
    })

    // 添加关闭hook
    Runtime.getRuntime.addShutdownHook(new Thread(() => {
      println("正在关闭streams...")
      streams.close(Duration.ofSeconds(5))
      latch.countDown()
      println("Streams已关闭")
    }))

    // 启动streams
    println("正在启动Kafka Streams...")
    streams.start()
    println("Kafka Streams已启动，等待接收数据...")
    latch.await()
  }
}
