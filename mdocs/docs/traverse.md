# Traverse

convert `List[A]` to `F[List[B]]` using `A => F[B]`

traverse works for any traversable type that implements `Traversable` type-class `cats.Traversable`

most common instances are `List` `Option` - these are included in cats library and are accessible after including
```scala
import cats.implicits._
```

(not `Seq`! - need to be converted to List or Vector in order to work)

traverse requires `F[_]` in `A => F[B]` to be Applicative (be an instance of Applicative type-class - "implementing Applicative") `cats.Applicative`

all standard scala types that are `Applicative` (Future, Option, Either, List ....) will satisfy this requirement after importing `cats.implicits._`


### Examples
```scala mdoc
import cats.implicits._
import scala.concurrent.Future 
import scala.concurrent.ExecutionContext.Implicits.global

def compute(n: Int): Future[Int] = Future(n)

val list = (1 to 10).toList

list.traverse(n => compute(n))
``` 

```scala mdoc
import cats.implicits._ 

def parseInt(s: String): Option[Int] = Either.catchOnly[NumberFormatException](s.toInt).toOption

List("1", "2", "3").traverse(parseInt)

List("1", "two", "3").traverse(parseInt)
```

### Traverse isn't parallel

```scala mdoc
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

def effect(n: Int): Future[Unit] = {
  Thread.sleep(1000 / n) // bigger the number, shorter it takes
  Future(println(n))
}

def program = (1 to 10).toList.traverse(effect)


Await.result(program, 1.minute)
```

### Traverse filter

filtering out values that are not present while traversing in `F[_]` context

```scala mdoc

import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val users: Map[Int, String] = Map(1 -> "Mike", 3 -> "Martin", 6 -> "John")

def getUser(id: Int): Future[Option[String]] = Future(users.get(id))

val userIds = (1 to 10).toList

userIds.traverseFilter(getUser)

```