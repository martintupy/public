package circe.discriminator

import circe.discriminator.JsonSerializers._

sealed trait Outer {
  def inner: Inner
}

object Outer {
  implicit val decoder: Decoder[Outer] = deriveDecoder[Outer]
  implicit val encoder: Encoder[Outer] = deriveEncoder[Outer]

  case class OuterA(inner: Inner) extends Outer
  case class OuterB(inner: Inner, num: Int = 0) extends Outer
  case class OuterC(inner: Inner, name: String) extends Outer
}
