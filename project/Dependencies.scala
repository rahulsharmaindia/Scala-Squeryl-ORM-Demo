import sbt._

object Dependencies {
  val squerylVersion = "0.9.13"
  val postgresqlVersion = "42.2.2"
  val h2Version = "1.3.166"
  val squeryl = "org.squeryl" %% "squeryl" % squerylVersion % "compile" exclude ("cglib","cglib-nodep") withSources()

  val cglib = "cglib" % "cglib-nodep" % "3.2.10" withSources()
  val postgresql = "org.postgresql" % "postgresql" % postgresqlVersion % "runtime" withSources()
  //val h2 = "com.h2database" % "h2" % h2Version withSources()
 // val cglib = "cglib" % "cglib-nodep" % "3.2.10" withSources()
}