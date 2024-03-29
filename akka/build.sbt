name := "akkaTest"
version := "0.1"

scalaVersion := "3.0.1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
/* scalaTest */
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"

//com.typesafe.akka::akka-actor-typed:2.6.15
val AkkaVersion = "2.6.15"
libraryDependencies += "com.typesafe.akka" % "akka-actor-typed_2.13" % AkkaVersion
libraryDependencies += "com.typesafe.akka" % "akka-cluster-typed_2.13" % AkkaVersion

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

mainClass := Some("hx.FxTest")

assembly / assemblyMergeStrategy  := {
  case PathList("scalactic") => MergeStrategy.discard
  case PathList("scalatest") => MergeStrategy.discard
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case x => MergeStrategy.first
}
