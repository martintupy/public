package circe.discriminator

import circe.discriminator.JsonSerializers._

sealed trait Inner {
  def name: String
}

object Inner {
  case class InnerA(i: Int, name: String) extends Inner
  case class InnerB(s: String, name: String) extends Inner

  implicit val encoder: Encoder[Inner] = deriveEncoder
  implicit val decoder: Decoder[Inner] = deriveDecoder
}


