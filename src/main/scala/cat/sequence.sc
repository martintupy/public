
import cats.implicits._

List(Option(1), Option(2), Option(3)).sequence

List(Option(1), Option.empty[Int], Option(3)).sequence