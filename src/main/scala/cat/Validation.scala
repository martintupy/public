package cat

import cats.data.Validated
import cats.implicits._

object Validation extends App {

  Validated.Valid(1).andThen(a => Validated.Valid(1 + a))
}
