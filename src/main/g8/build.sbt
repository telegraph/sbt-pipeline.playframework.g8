import Dependencies._
import sbt.Keys._

// give the user a nice default project!
lazy val buildNumber = sys.env.get("BUILD_NUMBER").map( bn => s"b\$bn")

resolvers += "mvn-artifacts" at "s3://mvn-artifacts/release"

lazy val CommonSettings = Seq(
  name          := "$name;format="normalize"$",
  organization  := "$organization$",
  version       := "1.0.0-" + buildNumber.getOrElse("dev"),
  scalaVersion  := "$scala_version$",
  isSnapshot    := buildNumber.isEmpty,
  scalacOptions += "-target:jvm-1.8"
)

lazy val root = (project in file(".")).
  configs ( IntegrationTest         ).
  settings( Defaults.itSettings: _* ).
  settings( CommonSettings     : _* ).
  enablePlugins(PlayScala).
  settings(
    (sourceDirectory in IntegrationTest) := (baseDirectory.value / "it"),
    (resourceDirectories in IntegrationTest) += (baseDirectory.value / "it"),
    (stackCustomParams in DeployDev    ) += ("BuildVersion" -> version.value),
    (stackCustomParams in DeployPreProd) += ("BuildVersion" -> version.value),
    (stackCustomParams in DeployProd   ) += ("BuildVersion" -> version.value)
  )

(testFrameworks in IntegrationTest) += new TestFramework("com.waioeka.sbt.runner.CucumberFramework")

publishTo := {
  if( isSnapshot.value ){
    Some("mvn-artifacts" at "s3://mvn-artifacts/snapshot")
  }else{
    Some("mvn-artifacts" at "s3://mvn-artifacts/release")
  }
}

resolvers += "mvn-artifacts" at "s3://mvn-artifacts/release"

libraryDependencies ++=
  Seq(
    cache,
    ws,
    filters,
    specs2 % Test) ++
  ProjectDependencies ++
  UnitTestDependencies ++
  IntTestDependencies
