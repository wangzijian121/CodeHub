//项目版本
ThisBuild / version := "1.0.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.3"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "scala"

  )
//使用UTF-8
javacOptions ++= Seq("-encoding", "UTF-8")