import Resolvers._
import Dependencies._

val buildSettings = Seq(
  organization := "org.squerylDemo",
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

scalacOptions ++= PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion.value)) {
  case Some((2, v)) if v >= 11 => unusedWarnings
}.toList.flatten

Seq(Compile, Test).flatMap(c =>
  scalacOptions in(c, console) --= unusedWarnings
)

lazy val projDeps = Seq(
  h2,
  cglib,
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

