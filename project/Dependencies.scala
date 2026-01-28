import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "ch.qos.logback"       % "logback-classic"         % "1.5.26" % Test,
    "com.vladsch.flexmark" % "flexmark-all"            % "0.64.8" % Test,
    "org.scalatest"       %% "scalatest"               % "3.2.19" % Test,
    "uk.gov.hmrc"         %% "ui-test-runner"          % "0.52.0" % Test,
    "org.mongodb"          % "mongodb-driver-sync"     % "5.6.2"  % Test,
    "com.typesafe.play"   %% "play-json"               % "2.10.8" % Test,
    "uk.gov.hmrc.mongo"   %% "hmrc-mongo-test-play-30" % "2.12.0" % Test
  )
}
