package cat
import cats.effect.{ExitCode, IO, IOApp}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureIO extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    println("start")
    val io = IO { Thread.sleep(1000); println("I am IO") }

    io.unsafeRunSync()
    io.start.unsafeRunAsyncAndForget()

    Future { println("I am Future") }

    Thread.sleep(10000)
    IO(ExitCode.Success)
  }
}
