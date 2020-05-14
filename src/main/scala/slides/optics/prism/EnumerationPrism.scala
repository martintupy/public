package slides.optics.prism

object EnumerationPrism {

  object ColorEnum extends Enumeration {
    val Red, Green, Blue = Value
  }


  val redPrism = new Prism[ColorEnum.Value, ColorEnum.Red.type] {
    def getOption(s: ColorEnum.Value): Option[ColorEnum.Red.type] = s match {
      case ColorEnum.Red => Some(ColorEnum.Red)
      case _ => None
    }

    def reverseGet(a: ColorEnum.Red.type): ColorEnum.Value = a
  }
}
