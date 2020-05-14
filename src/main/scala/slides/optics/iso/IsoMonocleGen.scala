package slides.optics.iso

import monocle.macros.GenIso

object IsoMonocleGen {

  case class JNum(n: Double)

  val jNumDoubleIso = GenIso[JNum, Double]
}
