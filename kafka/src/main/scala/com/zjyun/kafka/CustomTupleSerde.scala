package com.zjyun.kafka

import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer}
import java.lang.{Double, Long}
import java.nio.ByteBuffer
import java.util

object CustomTupleSerde extends Serde[(Double, Long)] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def close(): Unit = {}

  override def serializer(): Serializer[(Double, Long)] = new Serializer[(Double, Long)] {
    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

    override def close(): Unit = {}

    override def serialize(topic: String, data: (Double, Long)): Array[Byte] = {
      if (data == null) return null
      val buffer = ByteBuffer.allocate(16) // 分配16字节的缓冲区
      buffer.putDouble(data._1.doubleValue()) // 确保使用doubleValue()
      buffer.putLong(data._2.longValue())    // 确保使用longValue()
      buffer.array()
    }
  }

  override def deserializer(): Deserializer[(Double, Long)] = new Deserializer[(Double, Long)] {
    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

    override def close(): Unit = {}

    override def deserialize(topic: String, data: Array[Byte]): (Double, Long) = {
//      if (data == null || data.length < 1) return (0.0, 0L) // 添加长度检查
      val buffer = ByteBuffer.wrap(data)
      try {
        val sum = buffer.getDouble()
        val count = buffer.getLong()
        (sum, count)
      } catch {
        case e: Exception =>
          println(s"反序列化错误: ${e.getMessage}")
          (0.0, 0L) // 发生错误时返回默认值
      }
    }
  }
}