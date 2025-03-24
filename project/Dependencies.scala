import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "ch.qos.logback"       % "logback-classic"         % "1.5.18" % Test,
    "com.vladsch.flexmark" % "flexmark-all"            % "0.64.8" % Test,
    "org.scalatest"       %% "scalatest"               % "3.2.19" % Test,
    "uk.gov.hmrc"         %% "ui-test-runner"          % "0.45.0" % Test,
    "org.mongodb.scala"   %% "mongo-scala-driver"      % "5.4.0"  % Test,
    "com.typesafe.play"   %% "play-json"               % "2.10.6" % Test,
    "uk.gov.hmrc.mongo"   %% "hmrc-mongo-test-play-30" % "2.6.0"  % Test
  )
}
