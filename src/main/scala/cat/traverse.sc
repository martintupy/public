
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

def compute(n: Int): Future[Int] = Future(n)

val list = (1 to 10).toList

list.traverse(n => compute(n))

// ---------------
def parseInt(s: String): Option[Int] =
  Either.catchOnly[NumberFormatException](s.toInt).toOption

List("1", "2", "3").traverse(parseInt)

List("1", "two", "3").traverse(parseInt)

val users: Map[Int, String] = Map(1 -> "Mike", 3 -> "Martin", 6 -> "John")