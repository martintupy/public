### Used libraries

- [back to Index](index.md)

---
cats

https://github.com/typelevel/cats
```scala
libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"
```

---

monocle

https://github.com/optics-dev/Monocle
```scala
libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"    % "2.0.0",
  "com.github.julien-truffaut" %%  "monocle-macro"   % "2.0.0",
  "com.github.julien-truffaut" %%  "monocle-generic" % "2.0.0",
  "com.github.julien-truffaut" %%  "monocle-law"     % "2.0.0" % "test"
)
```
---
fs2

https://github.com/functional-streams-for-scala/fs2
```scala
libraryDependencies += "co.fs2" %%% "fs2-core" % "2.1.0"
```
---
circe

https://github.com/circe/circe
```scala
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % "0.13.0"
  "io.circe" %% "circe-generic" % "0.13.0"
  "io.circe" %% "circe-parser" % "0.13.0"
  "io.circe" %% "circe-shapes" % "0.13.0"
  "io.circe" %% "circe-generic-extras" % "0.13.0"
)
```