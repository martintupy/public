package circe.discriminator
import io.circe.{Decoder, Encoder}

object Gender extends Enumeration {
  val Male = Value("male")
  val Female = Value("female")
  val Other = Value("other")
  implicit val encoder: Encoder[Value] = Encoder.enumEncoder(Gender)
  implicit val decoder: Decoder[Value] = Decoder.enumDecoder(Gender)
}

