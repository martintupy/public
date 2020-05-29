import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val usersGroup: Map[Int, List[String]] = Map(1 -> List("Mike", "Josh", "Maria"), 3 -> List("Martin"), 6 -> List("John", "Peter"))

def getGroupUsers(id: Int): Future[List[String]] = Future(usersGroup.getOrElse(id, List.empty))

val userIds = (1 to 10).toList

userIds.flatTraverse(getGroupUsers)