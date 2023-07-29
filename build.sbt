name := """first-project"""
organization := "com.first"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.13-play27"
)
libraryDependencies += ws
//mysql dependencies
libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "8.0.12",
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0")
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.first.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.first.binders._"

