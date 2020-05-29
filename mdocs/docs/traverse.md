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

traverse is not spawning new threads, every value in `Traversable` is processed sequentially 

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

filtering out values that are not present as result while traversing in `F[_]` context

```scala mdoc

import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val users: Map[Int, String] = Map(1 -> "Mike", 3 -> "Martin", 6 -> "John")

def getUser(id: Int): Future[Option[String]] = Future(users.get(id))

val userIds = (1 to 10).toList

userIds.traverseFilter(getUser)

```

### Flat traverse

flattening values (list of values) from result while traversing in `F[_]` context

```scala mdoc
import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val usersGroup: Map[Int, List[String]] = Map(1 -> List("Mike", "Josh", "Maria"), 3 -> List("Martin"), 6 -> List("John", "Peter"))

def getGroupUsers(id: Int): Future[List[String]] = Future(usersGroup.getOrElse(id, List.empty))

List(1, 2, 3).flatTraverse(getGroupUsers)

```

### All together

Without `F[_]` context
```scala mdoc
val appToRole: Map[String, List[String]] = Map("fevo" -> List("Admin", "User", "Customer"), "other" -> List("User"))
val roleToUserId: Map[String, List[Int]] = Map("Admin" -> List(1, 3, 4), "User" -> List(1, 2, 3, 4, 5, 6), "Customer" -> List.empty)
val userIdToUser: Map[Int, String] = Map(1 -> "Martin", 2 -> "Leo", 3 -> "Peter", 4 -> "Maria", 5 -> "Josh", 6 -> "Dusan", 7 -> "John", 8 -> "Patrick")

def getRoles(app: String): List[String] = appToRole.getOrElse(app, List.empty)
def getUserIds(role: String): List[Int] = roleToUserId.getOrElse(role, List.empty)
def getUserName(id: Int): Option[String] =  userIdToUser.get(id)

for {
  role <- getRoles("fevo")
  id <- getUserIds(role)
  user <- getUserName(id).toList
} yield user

for {
  role <- getRoles("other")
  id <- getUserIds(role)
  user <- getUserName(id).toList
} yield user
```

With `F[_]` context - `Future` standard scala
```scala mdoc
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

def getRolesF(app: String): Future[List[String]] = Future(getRoles(app))
def getUserIdsF(role: String): Future[List[Int]] = Future(getUserIds(role))
def getUserNameF(id: Int): Future[Option[String]] =  Future(getUserName(id))


val usersStd = for {
  roles <- getRolesF("other")
  ids <- Future.traverse(roles)(getUserIdsF).map(_.flatten)
  user <- Future.traverse(ids)(id => getUserNameF(id)).map(_.flatten)
} yield user
Await.result(usersStd, 1.minute)
```

With `F[_]` context - `Future` using cats
```scala mdoc
import cats.implicits._

val usersCats = for {
  roles <- getRolesF("other") 
  userIds <- roles.flatTraverse(getUserIdsF)
  user <- userIds.traverseFilter(getUserNameF)
} yield user

Await.result(usersCats, 1.minute)
```