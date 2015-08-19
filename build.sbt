net.virtualvoid.sbt.graph.Plugin.graphSettings

name := "pacman"

version := "1.0"

scalaVersion := "2.11.6"

javacOptions in compile ++= Seq("-target", "7", "-source", "7")

EclipseKeys.withSource := true

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.6.4" % "test",
                            "org.specs2" %% "specs2-matcher-extra" % "3.6.4" % "test")


scalacOptions in Test ++= Seq("-Yrangepos")

mainClass in (Compile, run) := Some("pacman.PacmanApp")
