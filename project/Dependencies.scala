import sbt._

object Versions {
  lazy val cats = "2.0.0"
  lazy val fs2 = "2.1.0"
  lazy val circe = "0.13.0"
  lazy val monocle = "2.0.0"
}

object Dependencies {
  lazy val scalaTest = Seq("org.scalatest" %% "scalatest" % "3.0.8" % Test)
  lazy val scalaCheck = Seq("org.scalacheck" %% "scalacheck" % "1.14.1")
  lazy val cats = Seq("org.typelevel" %% "cats-core" % Versions.cats)
  lazy val fs2 = Seq("co.fs2" %% "fs2-io" % Versions.fs2)
  lazy val monocle = Seq(
    "com.github.julien-truffaut" %% "monocle-core",
    "com.github.julien-truffaut" %% "monocle-generic",
    "com.github.julien-truffaut" %% "monocle-macro",
    "com.github.julien-truffaut" %% "monocle-law"
  ).map(_ % Versions.monocle)

  lazy val circe = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser",
    "io.circe" %% "circe-shapes",
    "io.circe" %% "circe-generic-extras"
  ).map(_ % Versions.circe)
}
