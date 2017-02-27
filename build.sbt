
resolvers += "Typesafe Resolver" at "http://repo.typesafe.com/typesafe/simple/maven-releases/"

scriptedBufferLog := false

lazy val root = (project in file(".")).
  settings(
    name         := "pipeline-service.g8",
    organization := "uk.co.telegraph",
    version      := "1.0.0",
    scalaVersion := "2.11.8",
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers          += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
  )
