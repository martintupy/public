import Dependencies._

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "showcase",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.typelevel" %% "cats-core" % "2.0.0",
      "com.github.julien-truffaut" %%  "monocle"  % "2.0.0",
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
