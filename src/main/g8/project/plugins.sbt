logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "mvn-artifacts" atS3 "s3://mvn-artifacts/release"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.10")
addSbtPlugin("uk.co.telegraph"   % "sbt-pipeline-plugin" % "1.1.0-b+")
