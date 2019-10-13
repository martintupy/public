package optics.prism

object SealedTraitPrism extends App {
  sealed trait Json
  case object JNull extends Json
  case class JStr(v: String) extends Json
  case class JNum(v: Double) extends Json
  case class JBool(v: Boolean) extends Json

  val jsonDoublePrism = new Prism[Json, Double] {
    def getOption(s: Json): Option[Double] = s match {
      case JNum(num) => Some(num)
      case _ => None
    }
    def reverseGet(a: Double): Json = JNum(a)
  }

  val doubleIntPrism = new Prism[Double, Int] {
    def getOption(s: Double): Option[Int] = if (s.isValidInt) Some(s.toInt) else None
    def reverseGet(a: Int): Double = a.toDouble
  }

  val jsonIntPrism: Prism[Json, Int] = jsonDoublePrism composePrism doubleIntPrism

  println(jsonIntPrism.modify(_ + 5)(JNum(10.1)))
  println(jsonIntPrism.modify(_ + 5)(JNum(10)))

}
