import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalacOptions ++= Seq(
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions"
)

lazy val root = (project in file("."))
  .settings(
    name := "public",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      cats,
      scalaCheck,
      fs2
    ) ++ monocle ++ circe
  )
