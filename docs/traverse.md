### Traverse isn't parallel

```scala
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

def effect(n: Int): Future[Unit] = {
  Thread.sleep(1000 / n) // bigger the number, shorter it takes
  Future(println(n))
}

def program = (1 to 10).toList.traverse(effect)


Await.result(program, 1.minute)
// 1
// 2
// 3
// 4
// 5
// 6
// 7
// 8
// 9
// 10
// res0: List[Unit] = List((), (), (), (), (), (), (), (), (), ())
```

