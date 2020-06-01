# Traverse

convert `List[A]` to `F[List[B]]` using `A => F[B]`

traverse works for any traversable type that implements `Traverse` type-class `cats.Traverse`

most common instances are `List` `Option` - these are included in cats library and are accessible after including
```scala
import cats.implicits._
```

(not `Seq`! - need to be converted to List or Vector in order to work)

traverse requires `F[_]` in `A => F[B]` to be Applicative (be an instance of Applicative type-class - "implementing Applicative") `cats.Applicative`

all standard scala types that are `Applicative` (Future, Option, Either, List ....) will satisfy this requirement after importing `cats.implicits._`


## `Traverse.traverse`
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

## `Traverse.traverseFilter`

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

## `Traverse.flatTraverse`

flattening values (list of values) from result while traversing in `F[_]` context

```scala mdoc
import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val usersGroup: Map[Int, List[String]] = Map(1 -> List("Mike", "Josh", "Maria"), 3 -> List("Martin"), 6 -> List("John", "Peter"))

def getGroupUsers(id: Int): Future[List[String]] = Future(usersGroup.getOrElse(id, List.empty))

List(1, 2, 3).flatTraverse(getGroupUsers)

```

## `Traverse.sequence`

sequence is just like traverse, but done after List is being created (traverse does it one go)

convert existing `List[F[A]]` to `F[List[A]]` 

looking at the implementation, it is just traverse called with identity
```scala
def sequence[G[_]: Applicative, A](fga: F[G[A]]): G[F[A]] =
  traverse(fga)(ga => ga)
``` 

```scala mdoc 
import cats.implicits._

List(Option(1), Option(2), Option(3)).sequence

List(Option(1), Option.empty[Int], Option(3)).sequence
```

always prefer `traverse` over `sequence`


## All together

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

### FAQs

[traverse-faq](./traverse-faq.md)