package slides.types

object Coproduct extends App {

  sealed trait Color
  case object Red extends Color
  object Green extends Color
  object Blue extends Color


  def takeColor(color: Color) = println(color)

  takeColor(Red)

  case object White
  case object Black
  val blackOrWhite: Either[Black.type , White.type] = Left(Black)
  // val blackOrWhite: Either[Black, White] = Right(Black) // nope
  // val blackOrWhite: Either[Black, White] = Right(White) // nope


}
