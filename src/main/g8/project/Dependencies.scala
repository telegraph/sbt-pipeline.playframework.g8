import sbt.Keys._
import sbt._

object Dependencies {

  val AkkaVersion     = "2.4.17"
  val AkkaHttpVersion = "10.0.5"
  val CucumberVersion = "1.2.5"
  val LogBackVersion  = "1.2.3"
  val TmgUtils        = "1.0.0-b37"
  val Json4sVersion   = "3.5.1"
  val PlayVersion     = "2.5.14"
  val JacksonVersion  = "2.7.8"

  val Common = Seq(
    libraryDependencies ++= Seq(
      "io.swagger"                    %%  "swagger-play2"           % "1.5.3" excludeAll(
        ExclusionRule("com.typesafe.play"),
        ExclusionRule("com.fasterxml.jackson.module ")
      ),
      "com.google.inject.extensions"  %   "guice-multibindings"     % "4.0",

      // Logging
      "ch.qos.logback"                % "logback-core"              % LogBackVersion,
      "ch.qos.logback"                % "logback-classic"           % LogBackVersion,
      "net.logstash.logback"          % "logstash-logback-encoder"  % "4.9",
      "com.typesafe.play"             %% "routes-compiler"          % PlayVersion     % Compile,

      // Test dependencies
      "org.scalatest"                 %%  "scalatest"               % "3.0.3"         % Test,
      "org.mockito"                   %   "mockito-core"            % "2.7.22"        % Test,

      "com.waioeka.sbt"               %%  "cucumber-runner"         % "0.0.5"         % IntegrationTest,
      "org.scala-sbt"                 %   "test-interface"          % "1.0"           % IntegrationTest,
      "info.cukes"                    %   "cucumber-core"           % CucumberVersion % IntegrationTest,
      "info.cukes"                    %   "cucumber-jvm"            % CucumberVersion % IntegrationTest,
      "info.cukes"                    %   "cucumber-junit"          % CucumberVersion % IntegrationTest,
      "info.cukes"                    %   "cucumber-java"           % CucumberVersion % IntegrationTest,
      "info.cukes"                    %%  "cucumber-scala"          % CucumberVersion % IntegrationTest,
      "io.rest-assured"               %   "scala-support"           % "3.0.2"         % IntegrationTest
    ),
    dependencyOverrides ++= Set(
      "com.fasterxml.jackson.module"  %% "jackson-module-scala"     % JacksonVersion,
      "com.fasterxml.jackson.core"    %% "jackson-core"             % JacksonVersion
    )
  )

  val ServiceDependencies = Seq(
    libraryDependencies ++= Seq(
      // Json4s
      "org.json4s"              %% "json4s-jackson"       % Json4sVersion excludeAll ExclusionRule("com.fasterxml.jackson.core"),
      "org.json4s"              %% "json4s-core"          % Json4sVersion,
      "org.json4s"              %% "json4s-ext"           % Json4sVersion,

      "uk.co.telegraph"         %%  "generic-client"      % TmgUtils,
      "uk.co.telegraph"         %%  "http-client"         % TmgUtils,
      "uk.co.telegraph"         %%  "play-server-ext"     % TmgUtils,
      "com.typesafe.play"       %%  "filters-helpers"     % PlayVersion,

      // Akka
      "com.typesafe.akka"       %% "akka-http"                     % AkkaHttpVersion,
      "com.typesafe.akka"       %% "akka-actor"                    % AkkaVersion,
      "com.typesafe.akka"       %% "akka-slf4j"                    % AkkaVersion,
      "com.typesafe.akka"       %% "akka-stream"                   % AkkaVersion,
      "com.typesafe.akka"       %% "akka-http-testkit"             % AkkaHttpVersion % Test,
      "com.typesafe.akka"       %% "akka-stream-testkit"           % AkkaVersion     % Test,

      "com.fasterxml.jackson.module" %% "jackson-module-scala"     % JacksonVersion
    )) ++ Common
}
