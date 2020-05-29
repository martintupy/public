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
    libraryDependencies ++=
      Dependencies.scalaTest ++
      Dependencies.cats ++
      Dependencies.scalaCheck ++
      Dependencies.fs2 ++
      Dependencies.monocle ++
      Dependencies.circe
  )

lazy val mdocs = project
  .dependsOn(root)
  .settings(
    mdocVariables := Map(
      "CATS" -> Versions.cats,
      "FS2" -> Versions.fs2,
      "CIRCE" -> Versions.circe,
      "MONOCLE" -> Versions.monocle
    ),
    mdocOut := file("docs"),
    mdocIn := file("mdocs/docs")
  )
  .enablePlugins(MdocPlugin)
