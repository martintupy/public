package optics.prism

import monocle.Prism
import monocle.macros.GenPrism

object MonoclePrism {

  sealed trait Json
  case object JNull extends Json
  case class JStr(v: String) extends Json
  case class JNum(v: Double) extends Json
  case class JBool(v: Boolean) extends Json

  val jStr = Prism[Json, String] {
    case JStr(v) => Some(v)
    case _       => None
  } (JStr)

  val jNum = Prism.partial[Json, Double] { case JNum(num) => num } { num => JNum(num) }

  val jNumGen = GenPrism[Json, JNum]

  sealed trait Sum
  object Sum {
    case class Inter(json: Json) extends Sum
  }

  val sumInter = GenPrism[Sum, Sum.Inter]
  //  val sumJNum = GenPrism[Sum, JNum] won't work
}
