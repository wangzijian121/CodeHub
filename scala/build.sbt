//
name := "scala" // 项目名称
organization := "zjyun.cc" // 组织名称
//项目版本
version := "1.0.0-SNAPSHOT"
scalaVersion := "2.13.15"


lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "scala"
  )
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  "com.github.sbt" % "junit-interface" % "0.13.3" % "compile",
  //"org.seleniumhq.selenium" % "selenium-java" % "4.28.1",
  "org.apache.spark" % "spark-core_2.12" % "3.5.4"
)