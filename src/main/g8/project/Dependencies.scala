
import sbt._

object Dependencies {

  private lazy val JUnit     = "junit"         %  "junit"     % "4.12"
  private lazy val Scalatest = "org.scalatest" %% "scalatest" % "3.0.1"

  lazy val ProjectDependencies = Seq(
    "io.swagger" %% "swagger-play2" % "1.5.3",
    "uk.co.telegraph" %% "tmgplugin-play" % "1.0.+",
    "net.logstash.logback" % "logstash-logback-encoder" % "4.0"
  )

  lazy val UnitTestDependencies = Seq(
    JUnit     % Test,
    Scalatest % Test
  )

  lazy val IntTestDependencies = Seq(
    JUnit     % IntegrationTest,
    Scalatest % IntegrationTest,
    
    // Cucumber Runner
    "com.waioeka.sbt"  %% "cucumber-runner"       % "0.0.5"  % IntegrationTest,

    "org.scala-sbt"    % "test-interface"         % "1.0"    % IntegrationTest,
    "info.cukes"       %  "cucumber-core"         % "1.2.5"  % IntegrationTest,
    "info.cukes"       %  "cucumber-jvm"          % "1.2.5"  % IntegrationTest,
    "info.cukes"       %  "cucumber-junit"        % "1.2.5"  % IntegrationTest,
    "info.cukes"       %% "cucumber-scala"        % "1.2.5"  % IntegrationTest,
    "net.serenity-bdd" %  "serenity-junit"        % "1.2.2"  % IntegrationTest,
    "net.serenity-bdd" %  "serenity-cucumber"     % "1.1.23" % IntegrationTest,
    "net.serenity-bdd" %  "serenity-rest-assured" % "1.2.2"  % IntegrationTest
  )
}