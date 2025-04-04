lazy val root = (project in file("."))
  .settings(
    name := "digital-platform-reporting-manage-ui-tests",
    version := "0.1.0",
    scalaVersion := "2.13.16",
    libraryDependencies ++= Dependencies.test,
    (Compile / compile) := ((Compile / compile) dependsOn (Compile / scalafmtSbtCheck, Compile / scalafmtCheckAll)).value
  )
  .settings(Test / parallelExecution := false)
  .disablePlugins(JUnitXmlReportPlugin)
