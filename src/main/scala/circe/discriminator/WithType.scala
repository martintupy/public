package circe.discriminator

import circe.discriminator.JsonSerializers._

sealed trait WithType {
  def `type`: String
}

object WithType {

  case class WithTypeA(`type`: String) extends WithType
  case class WithTypeB(`type`: String, num: Int) extends WithType

  implicit val decoder: Decoder[WithType] = deriveDecoder
  implicit val encoder: Encoder[WithType] = deriveEncoder
}
