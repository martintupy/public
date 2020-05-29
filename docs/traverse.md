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
```scala
import cats.implicits._
import scala.concurrent.Future  
import scala.concurrent.ExecutionContext.Implicits.global

def compute(n: Int): Future[Int] = Future(n)

val list = (1 to 10).toList
// list: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

list.traverse(n => compute(n))
// res0: Future[List[Int]] = Future(Success(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
``` 

```scala
import cats.implicits._  

def parseInt(s: String): Option[Int] = Either.catchOnly[NumberFormatException](s.toInt).toOption

List("1", "2", "3").traverse(parseInt)
// res1: Option[List[Int]] = Some(List(1, 2, 3))

List("1", "two", "3").traverse(parseInt)
// res2: Option[List[Int]] = None
```

### Traverse isn't parallel

traverse is not spawning new threads, every value in `Traversable` is processed sequentially 

```scala
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
// 1
// 2
// 3
// 4
// 5
// 6
// 7
// 8
// 9
// 10
// res3: List[Unit] = List((), (), (), (), (), (), (), (), (), ())
```

### Traverse filter

filtering out values that are not present as result while traversing in `F[_]` context

```scala
import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val users: Map[Int, String] = Map(1 -> "Mike", 3 -> "Martin", 6 -> "John")
// users: Map[Int, String] = Map(1 -> "Mike", 3 -> "Martin", 6 -> "John")

def getUser(id: Int): Future[Option[String]] = Future(users.get(id))

val userIds = (1 to 10).toList
// userIds: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

userIds.traverseFilter(getUser)
// res4: Future[List[String]] = Future(Success(List(Mike, Martin, John)))
```

### Flat traverse

flattening values (list of values) from result while traversing in `F[_]` context

```scala
import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val usersGroup: Map[Int, List[String]] = Map(1 -> List("Mike", "Josh", "Maria"), 3 -> List("Martin"), 6 -> List("John", "Peter"))
// usersGroup: Map[Int, List[String]] = Map(
//   1 -> List("Mike", "Josh", "Maria"),
//   3 -> List("Martin"),
//   6 -> List("John", "Peter")
// )

def getGroupUsers(id: Int): Future[List[String]] = Future(usersGroup.getOrElse(id, List.empty))

List(1, 2, 3).flatTraverse(getGroupUsers)
// res5: Future[List[String]] = Future(Success(List(Mike, Josh, Maria, Martin)))
```