### Used libraries

- [back to Index](index.md)

---
cats

https://github.com/typelevel/cats
```scala
libraryDependencies += "org.typelevel" %% "cats-core" % "@CATS@"
```

---

monocle

https://github.com/optics-dev/Monocle
```scala
libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"    % "@MONOCLE@",
  "com.github.julien-truffaut" %%  "monocle-macro"   % "@MONOCLE@",
  "com.github.julien-truffaut" %%  "monocle-generic" % "@MONOCLE@",
  "com.github.julien-truffaut" %%  "monocle-law"     % "@MONOCLE@" % "test"
)
```
---
fs2

https://github.com/functional-streams-for-scala/fs2
```scala
libraryDependencies += "co.fs2" %%% "fs2-core" % "@FS2@"
```
---
circe

https://github.com/circe/circe
```scala
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % "@CIRCE@"
  "io.circe" %% "circe-generic" % "@CIRCE@"
  "io.circe" %% "circe-parser" % "@CIRCE@"
  "io.circe" %% "circe-shapes" % "@CIRCE@"
  "io.circe" %% "circe-generic-extras" % "@CIRCE@"
)
```