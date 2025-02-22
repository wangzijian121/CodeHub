package com.zjyun.spark.wordcount

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.log4j.Logger

case class FileUtils(path:String) {
  @transient lazy val log = Logger.getLogger(this.getClass)

  def deletePath= {
    log.info(s"删除！$path")
    val fileSystem = FileSystem.get(new Configuration())
    val pathDelete = new Path(path)
    if(fileSystem.exists(pathDelete)) fileSystem.delete(pathDelete,true)
  }
}

object FileUtils{
  implicit def  str2FileUtils(s:String)= new FileUtils(s)
}