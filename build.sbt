name := "buysomegames"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

lazy val versions = new {
  val finatra = "2.7.0"
  val logback = "1.1.7"
  val reactivemongo = "0.11.14"
  val specs2 = "3.7"
  val scalatest = "3.0.0"
  val guice = "4.1.0"
  val mongoscaladriver = "1.1.1"
  val twitterbijection = "0.9.2"
}

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % versions.finatra,
  "com.twitter" %% "finatra-slf4j" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % versions.logback,
  "org.mongodb.scala" %% "mongo-scala-driver" % versions.mongoscaladriver,
  "com.twitter" %% "bijection-core" % versions.twitterbijection,
  "com.twitter" %% "bijection-util" % versions.twitterbijection,

  "com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra % "test",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests",

  "org.scalatest" %% "scalatest" % versions.scalatest % "test",
  "org.specs2" %% "specs2" % versions.specs2 % "test"
)