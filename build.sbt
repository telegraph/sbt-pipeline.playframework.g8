// give the user a nice default project!
lazy val buildNumber = sys.env.get("BUILD_NUMBER").map( bn => s"b$bn")

lazy val root = (project in file("."))
  .settings(
    name          := "sbt-pipeline-playframework.g8",
    version       := "1.2.0" + buildNumber.getOrElse("SNAPSHOT"),
    test in Test  := {
      val _ = (g8Test in Test).toTask("").value
    },
    scriptedBufferLog  :=  false,
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers          +=  Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
  )
