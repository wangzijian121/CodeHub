package com.zjyun.spark.wordcount

import org.junit.{Assert, Test}
import com.zjyun.spark.rdd.wordcount.FileUtils.str2FileUtils
@Test
class FileUtilsTest extends Assert{

  @Test
  def   deletePath:Unit={
    val path ="/tmp/"
    path.deletePath
  }
}
