import cats.data.EitherT
import cats.implicits._
EitherT(3.asRight[String].some).getOrElse(2)
EitherT("AAA".asLeft[Int].some).getOrElse(2)