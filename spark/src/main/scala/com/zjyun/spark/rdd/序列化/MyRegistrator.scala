package com.zjyun.spark.rdd.序列化

import com.esotericsoftware.kryo.Kryo
import org.apache.hadoop.io.Text
import org.apache.spark.serializer.KryoRegistrator

class MyRegistrator extends KryoRegistrator {
  override def registerClasses(kryo: Kryo): Unit = {
    kryo.register(classOf[UserInfo])
    kryo.register(classOf[Text])
    kryo.register(Class.forName("scala.reflect.ClassTag$GenericClassTag"))
    kryo.register(classOf[Array[UserInfo]])
  }
}