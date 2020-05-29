
val appToRole: Map[String, List[String]] = Map("fevo" -> List("Admin", "User", "Customer"), "other" -> List("User"))
val roleToUserId: Map[String, List[Int]] = Map("Admin" -> List(1, 3, 4), "User" -> List(1, 2, 3, 4, 5, 6), "Customer" -> List.empty)
val userIdToUser: Map[Int, String] = Map(1 -> "Martin", 2 -> "Leo", 3 -> "Peter", 4 -> "Maria", 5 -> "Josh", 6 -> "Dusan", 7 -> "John", 8 -> "Patrick")

def getRoles(app: String): List[String] = appToRole.getOrElse(app, List.empty)
def getUserIds(role: String): List[Int] = roleToUserId.getOrElse(role, List.empty)
def getUser(id: Int): Option[String] =  userIdToUser.get(id)

for {
  role <- getRoles("fevo")
  id <- getUserIds(role)
  user <- getUser(id).toList
} yield user

for {
  role <- getRoles("other")
  id <- getUserIds(role)
  user <- getUser(id).toList
} yield user



import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

def getRolesF(app: String): Future[List[String]] = Future(getRoles(app))
def getUserIdsF(role: String): Future[List[Int]] = Future(getUserIds(role))
def getUserF(id: Int): Future[Option[String]] =  Future(getUser(id))


val usersStd = for {
  roles <- getRolesF("other")
  ids <- Future.traverse(roles)(getUserIdsF).map(_.flatten)
  user <- Future.traverse(ids)(id => getUserF(id)).map(_.flatten)
} yield user
Await.result(usersStd, 1.minute)


import cats.implicits._

val usersCats = for {
  roles <- getRolesF("fevo")
  userIds <- roles.flatTraverse(getUserIdsF)
  user <- userIds.traverseFilter(getUserF)
} yield user

Await.result(usersCats, 1.minute)