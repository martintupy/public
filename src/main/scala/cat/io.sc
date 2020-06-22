import cats.effect.{Blocker, ContextShift, IO}

import scala.concurrent.{ExecutionContext, Future}

val io = IO { println("I am IO") } // just instance containing a computation

io.unsafeRunSync()


Blocker.liftExecutionContext(scala.concurrent.ExecutionContext.global)
io.start.unsafeRunAsyncAndForget()
