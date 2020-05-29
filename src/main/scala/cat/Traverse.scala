package cat

import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Traverse extends App {

  def effect(n: Int): Future[Int] = {
    Thread.sleep(1000 / n) // bigger the number, shorter it takes
    println(n)
    n.pure[Future]
  }

  def program = (1 to 10).toList.traverse(effect)

  Await.result(program, 1.minute)

  // ---------------

  def compute(n: Int): Future[Int] = Future(n)

  val list = (1 to 10).toList

  list.traverse(n => compute(n))

  // ---------------
  def parseInt(s: String): Option[Int] = Either.catchOnly[NumberFormatException](s.toInt).toOption

  List("1", "2", "3").traverse(parseInt)

  List("1", "two", "3").traverse(parseInt)

  val users: Map[Int, String] = Map(1 -> "Mike", 3 -> "Martin", 6 -> "John")
}
