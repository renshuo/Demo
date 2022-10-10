enablePlugins(ScalaJSPlugin)

name := "Scala.js Tutorial"
scalaVersion := "2.13.1"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0"

scalaJSUseMainModuleInitializer := true
