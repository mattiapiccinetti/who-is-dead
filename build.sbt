name := """who-is-dead"""
organization := "com.peachofpersia"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala, JavaAppPackaging)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(guice, filters)
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
)