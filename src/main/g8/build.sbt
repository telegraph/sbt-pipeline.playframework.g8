import Dependencies._
import sbt.Keys._

// give the user a nice default project!
lazy val buildNumber = sys.env.get("BUILD_NUMBER").map( bn => s"b\$bn")

lazy val CommonSettings = Seq(
  name              := "$name;format="normalize"$",
  organization      := "$organization$",
  version           := "1.0.0-" + buildNumber.getOrElse("SNAPSHOT"),
  scalaVersion      := "$scala_version$",
  isSnapshot        := buildNumber.isEmpty,
  scalacOptions     ++= Seq(
    "-target:jvm-1.8",
    "-encoding", "UTF-8",
    "-feature"
  ),

  // Test & Coverage
  coverageExcludedPackages := ".*Reverse.*;.*Routes.*",
  publishMavenStyle := false
)

lazy val root = (project in file(".")).
  configs ( IntegrationTest         ).
  settings( Defaults.itSettings: _* ).
  settings( CommonSettings     : _* ).
  enablePlugins(PlayScala).
  disablePlugins(PlayLayoutPlugin).
  settings(
    PlayKeys.playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value,

    // ITest Config
    (testFrameworks    in IntegrationTest) += new TestFramework("com.waioeka.sbt.runner.CucumberFramework"),
    (wiremockVerbose   in IntegrationTest) := true,
    (wiremockRootDir   in IntegrationTest) := baseDirectory.value / "src"/ "it" / "resources" / "wiremock",
    (wiremockHttpPort  in IntegrationTest) := 19999,

    // Deployment
    (stackCustomParams in DeployDev    ) += ("BuildVersion" -> version.value),
    (stackCustomParams in DeployPreProd) += ("BuildVersion" -> version.value),
    (stackCustomParams in DeployProd   ) += ("BuildVersion" -> version.value),

    // Assembly
    mainClass             in assembly     := Some("play.core.server.ProdServerStart"),
    target                in assembly     := file("target"),
    assemblyJarName       in assembly     := s"\${name.value}-\${version.value}.jar",
    fullClasspath         in assembly     += Attributed.blank(PlayKeys.playPackageAssets.value),
    assemblyMergeStrategy in assembly     := {
      case "application.conf"             => MergeStrategy.concat
      case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    },
    test                  in assembly     := {},

    ServiceDependencies
  )

resolvers += "mvn-tmg-resolver" atS3 "s3://mvn-artifacts/release"
publishTo := {
  if( isSnapshot.value ){
    Some("mvn-tmg-publisher" at "s3://mvn-artifacts/snapshot")
  }else{
    Some("mvn-tmg-publisher" at "s3://mvn-artifacts/release")
  }
}
