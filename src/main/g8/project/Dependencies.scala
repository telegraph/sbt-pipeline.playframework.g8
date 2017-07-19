import sbt.Keys._
import sbt._

object Dependencies {

  val AkkaVersion     = "2.5.3"
  val AkkaHttpVersion = "10.0.9"
  val CucumberVersion = "1.2.5"
  val LogBackVersion  = "1.2.3"
  val TmgUtils        = "1.0.0-b54"
  val Json4sVersion   = "3.5.1"
  val PlayVersion     = "2.6.0"
  val GuiceVersion    = "4.1.0"
  val JacksonVersion  = "2.7.8"
  val AwsSdkVersion   = "1.11.159"

  val Common: Seq[Setting[_]]  = Seq(
    libraryDependencies ++= Seq(
      // Guice
      "com.google.inject"             % "guice"                         % GuiceVersion,

      // Logging
      "ch.qos.logback"                %  "logback-core"                 % LogBackVersion,
      "ch.qos.logback"                %  "logback-classic"              % LogBackVersion,
      "net.logstash.logback"          %  "logstash-logback-encoder"     % "4.11",

      // Json4s
      "org.json4s"                   %% "json4s-jackson"                % Json4sVersion,
      "org.json4s"                   %% "json4s-core"                   % Json4sVersion,
      "org.json4s"                   %% "json4s-ext"                    % Json4sVersion,
      "com.typesafe.akka"            %% "akka-http"                     % AkkaHttpVersion,
      "com.typesafe.akka"            %% "akka-actor"                    % AkkaVersion,
      "com.typesafe.akka"            %% "akka-slf4j"                    % AkkaVersion,
      "com.typesafe.akka"            %% "akka-stream"                   % AkkaVersion,

      // Telegraph Utils
      "uk.co.telegraph"              %%  "generic-client"               % TmgUtils,
      "uk.co.telegraph"              %%  "http-client"                  % TmgUtils,

      // Test dependencies
      "com.typesafe.akka"            %% "akka-testkit"                  % AkkaVersion     % Test,
      "com.typesafe.akka"            %% "akka-http-testkit"             % AkkaHttpVersion % Test,
      "com.typesafe.akka"            %% "akka-stream-testkit"           % AkkaVersion     % Test,
      "org.scalatest"                %%  "scalatest"                    % "3.0.3"         % Test,
      "org.scalamock"                %%  "scalamock-scalatest-support"  % "3.6.0"         % Test,
      "uk.co.telegraph"              %%  "http-client-testkit"          % TmgUtils        % Test
    ),
    dependencyOverrides ++= Set(
      "com.typesafe.akka"       %% "akka-http"                     % AkkaHttpVersion,
      "com.typesafe.akka"       %% "akka-actor"                    % AkkaVersion,
      "com.typesafe.akka"       %% "akka-slf4j"                    % AkkaVersion,
      "com.typesafe.akka"       %% "akka-stream"                   % AkkaVersion,
      "com.typesafe.akka"       %% "akka-testkit"                  % AkkaVersion     % Test,
      "com.typesafe.akka"       %% "akka-http-testkit"             % AkkaHttpVersion % Test,
      "com.typesafe.akka"       %% "akka-stream-testkit"           % AkkaVersion     % Test
    )
  )

  val ServiceDependencies: Seq[Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      "io.swagger"                   %%  "swagger-play2"                % "1.6.0-UPDATE-ME",
      "uk.co.telegraph"              %%  "play-server-ext"              % TmgUtils,
      "com.typesafe.play"            %%  "play"                         % PlayVersion,
      "com.typesafe.play"            %%  "play-guice"                   % PlayVersion,
      "com.amazonaws"                %   "aws-java-sdk-kms"             % AwsSdkVersion  excludeAll ExclusionRule("commons-logging", "commons-logging"),

      "com.google.inject.extensions" % "guice-multibindings"            % GuiceVersion
    )
  ) ++ Common

  val ComponentTests: Seq[Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      "org.scalatest"                 %%  "scalatest"               % "3.0.3",
      "io.rest-assured"               %   "scala-support"           % "3.0.3",
      "com.github.tomakehurst"        %   "wiremock"                % "2.6.0",
      "org.json4s"                    %%  "json4s-native"           % "3.5.2",
      "com.typesafe"                  %   "config"                  % "1.3.1"
    )
  )
}
