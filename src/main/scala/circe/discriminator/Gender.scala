package circe.discriminator
import io.circe.{Decoder, Encoder}

object Gender extends Enumeration {
  val Male, Female, Other = Value
  implicit val encoder: Encoder[Value] = Encoder.enumEncoder(Gender)
  implicit val decoder: Decoder[Value] = Decoder.enumDecoder(Gender)
}

