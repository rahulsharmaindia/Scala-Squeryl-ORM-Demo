import Resolvers._
import Dependencies._

val buildSettings = Seq(
  organization := "org.squerylTest",
  version := "2018-1",
  scalaVersion := "2.11.12"
)

javacOptions := Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xfuture",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:reflectiveCalls",
  "-language:existentials"
)

val unusedWarnings = Seq(
  "-Ywarn-unused"
)

scalacOptions ++= PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion.value)){
  case Some((2, v)) if v >= 11 => unusedWarnings
}.toList.flatten

Seq(Compile, Test).flatMap(c =>
  scalacOptions in (c, console) --= unusedWarnings
)

lazy val projDeps = Seq(
  h2,
 //cglib,
  //postgresql,
  squeryl
		)

lazy val squerylDemo = (project in file("."))
  .settings(
    buildSettings,
    resolvers := myResolvers,
    libraryDependencies ++= projDeps
  )
libraryDependencies ++= {
  Seq("org.scalatest" %% "scalatest" % "3.0.6-SNAP5" withSources())
}
/*
libraryDependencies ++= Seq(
  "cglib" % "cglib-nodep" % "3.2.10",
  "com.h2database" % "h2" % "1.4.197" % "provided",
  "mysql" % "mysql-connector-java" % "5.1.47" % "provided",
  "org.postgresql" % "postgresql" % "42.1.4.jre7" % "provided",
  "net.sourceforge.jtds" % "jtds" % "1.3.1" % "provided",
  "org.apache.derby" % "derby" % "10.11.1.1" % "provided",
  "org.xerial" % "sqlite-jdbc" % "3.25.2" ,
  "org.json4s" %% "json4s-scalap" % "3.6.2"
)*/
