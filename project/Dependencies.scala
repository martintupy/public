import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
  lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.14.1"
  lazy val cats = "org.typelevel" %% "cats-core" % "2.0.0"
  lazy val fs2 = "co.fs2" %% "fs2-io" % "2.1.0"
  lazy val monocle = Seq(
    "com.github.julien-truffaut" %% "monocle-core" % "2.0.0",
    "com.github.julien-truffaut" %% "monocle-generic" % "2.0.0",
    "com.github.julien-truffaut" %% "monocle-macro" % "2.0.0",
    "com.github.julien-truffaut" %% "monocle-law" % "2.0.0"
  )
}
