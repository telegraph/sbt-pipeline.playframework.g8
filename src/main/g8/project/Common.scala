import fm.sbt.S3Implicits._
import sbt.Keys.{publishTo, _}
import sbt.{AutoPlugin, _}
import scoverage.ScoverageKeys._

object Common extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  lazy val buildNumber: Option[String] = sys.env.get("BUILD_NUMBER").map(bn => s"b\$bn")

  override lazy val projectSettings: Seq[Setting[_]] = Seq(

    organization      := "$organization$",
    version           := "1.0.0-" + buildNumber.getOrElse("SNAPSHOT"),
    scalaVersion      := "$scala_version$",
    isSnapshot        := buildNumber.isEmpty,
    scalacOptions     ++= Seq(
      "-target:jvm-1.8",
      "-feature",
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      "-Xfuture"
    ),
    javacOptions ++= Seq(
      "-Xlint:unchecked"
    ),
    parallelExecution in Test := false,
    coverageFailOnMinimum     := false,
    coverageHighlighting      := true,
    coverageExcludedPackages  := ".*Reverse.*;.*Routes.*",
    autoAPIMappings           := true,

    resolvers                 += "mvn-tmg-resolver" atS3 "s3://mvn-artifacts/release",
    publishTo                 := {
      if( isSnapshot.value ){
        Some("mvn-artifacts" atS3 "s3://mvn-artifacts/snapshot")
      }else{
        Some("mvn-artifacts" atS3 "s3://mvn-artifacts/release")
      }
    }
  )
}
