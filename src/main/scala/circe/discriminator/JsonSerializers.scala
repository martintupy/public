package circe.discriminator

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.decoding.ConfiguredDecoder
import io.circe.generic.extras.encoding.ConfiguredObjectEncoder
import shapeless.Lazy

object JsonSerializers {

  implicit val circeConfiguration: Configuration = Configuration.default.withDefaults.withDiscriminator("type")

  type Decoder[A] = io.circe.Decoder[A]
  type Encoder[A] = io.circe.Encoder[A]

  def deriveDecoder[A](implicit decode: Lazy[ConfiguredDecoder[A]]) = io.circe.generic.extras.semiauto.deriveDecoder[A]
  def deriveEncoder[A](implicit encode: Lazy[ConfiguredObjectEncoder[A]]) = io.circe.generic.extras.semiauto.deriveEncoder[A]

}
