package com.zjyun.spark.streaming.a_spark_streaming算子

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.ReceiverInputDStream

/**
 * 《只使用最近更新的值》
 * 由于历史数据一直打印，如果未更新的数据一直写入到mysql中会大幅增加mysql 负载
 * 使用IsUpdate 包装对象来自定义数据状态，判断是否需要更新
 */
object UpdateStateByKeyCkptCheckTest {
  def main(args: Array[String]): Unit = {

    val ckptPath = "./ckpt"
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("sparkStreaming")
    //如果ckptPath 路径有历史数据则加载，否则走自行创建StreamingContext的逻辑
    val ssc = StreamingContext.getActiveOrCreate(ckptPath, () => {

      val ssc = new StreamingContext(conf, Seconds(5))
      //设置checkpoint文件夹
      ssc.checkpoint("./ckpt")
      val receiverInputDStream: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 6666)

      receiverInputDStream.transform((rdd, time) => {
          rdd.flatMap(_.split(" "))
            .map((_, IsUpdate(1, false))) //默认都不需要更新
          //.reduceByKey((a,b)=>IsUpdate(a.number+b.number,false))
        })
        //示例数据
        //(a,IsUpdate(1,false)) 有历史值
        //(a,IsUpdate(1,false))
        //(a,IsUpdate(1,false))
        //(b,IsUpdate(1,false))有历史值
        //(b,IsUpdate(1,false))
        //(c,IsUpdate(1,false)) 无历史值
        //curr为同一个key对应的值列表，last为历史值
        .updateStateByKey((curr: Seq[IsUpdate], last: Option[IsUpdate]) => {
          //按key计算总和
          val sumCurr = curr.map(_.number).sum
          //按key判断历史是否有值，有的话获取历史值，没有的历史为0
          val lastValue = if (last.isDefined) last.get.number else 0
          //核心逻辑，按照key判断key是否在当前批中，key要么是当前批新加进来的，要么是checkpoint 进来的。
          //如果是当前批进来的，就要计算新值然后更新。
          //如果是checkpoint 进来的，就要取历史值不更新。
          val res = if (sumCurr > 0) IsUpdate(sumCurr + lastValue, true) else IsUpdate(lastValue, false)
          Option(res)
        }
        ).print()
      ssc
    })
    ssc.start()
    ssc.awaitTermination()
  }
}

case class IsUpdate(number: Int, update: Boolean)