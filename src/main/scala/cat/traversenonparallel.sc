import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

def effect(n: Int): Future[Unit] = {
  Thread.sleep(1000 / n) // bigger the number, shorter it takes
  Future(println(n))
}

def program = (1 to 10).toList.traverse_(effect)

Await.result(program, 1.minute)