package cat

import java.util.concurrent.TimeUnit

import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Traverse extends App {

  def effect(n: Int): Future[Int] = {
    Thread.sleep(1000 / n) // bigger the number, longer it takes
    println(n)
    n.pure[Future]
  }

  def program = (1 to 10).toList.traverse(effect)


  Await.result(
    program,
    Duration(1, TimeUnit.MINUTES)
  )
  //  1
  //  2
  //  3
  //  4
  //  5
  //  6
  //  7
  //  8
  //  9
  //  10


}
