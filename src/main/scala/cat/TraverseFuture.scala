package cat
import java.util.concurrent.TimeUnit

import cats.Applicative
import com.twitter

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object TraverseFuture extends App {


  {
    def effect[A](a: A): Future[A] = {
      Future {
        Thread.sleep(1000)
        a
      }
    }

    val start: Long = System.currentTimeMillis()
    val result = Future.traverse((1 to 10).toList)(effect)
    Await.result(result, Duration(1, TimeUnit.MINUTES))
    val end: Long = System.currentTimeMillis()
    println(s"${end - start} ms")
  }

  {
    def effect[A](a: A): twitter.util.Future[A] = {
      twitter.util.Future {
        Thread.sleep(1000)
        a
      }
    }

    val start: Long = System.currentTimeMillis()
    val result = twitter.util.Future.traverseSequentially((1 to 10).toList)(effect)
    twitter.util.Await.result(result, twitter.util.Duration(1, TimeUnit.MINUTES))
    val end: Long = System.currentTimeMillis()
    println(s"${end - start} ms")
  }

  import cats.implicits._

  {
    def effect[A](a: A): Future[A] = {
      Future {
        Thread.sleep(1000)
        a
      }
    }

    val start: Long = System.currentTimeMillis()
    val result = (1 to 10).toList.traverse(effect)
    Await.result(result, Duration(1, TimeUnit.MINUTES))
    val end: Long = System.currentTimeMillis()
    println(s"${end - start} ms")
  }



  {

    implicit val twitterFutureApplicative: Applicative[twitter.util.Future] = new Applicative[twitter.util.Future] {
      def pure[A](x: A): twitter.util.Future[A] = twitter.util.Future.value(x)
      def ap[A, B](ff: twitter.util.Future[A => B])(fa: twitter.util.Future[A]): twitter.util.Future[B] = ff.join(fa).map { case (ab, a) => ab(a) }
    }
    def effect[A](a: A): twitter.util.Future[A] = {
      twitter.util.Future {
        Thread.sleep(1000)
        a
      }
    }

    val start: Long = System.currentTimeMillis()
    val result = (1 to 10).toList.traverse(effect)
    twitter.util.Await.result(result, twitter.util.Duration(1, TimeUnit.MINUTES))
    val end: Long = System.currentTimeMillis()
    println(s"${end - start} ms")
  }

}
