package optics.prism

object EitherPrism {

  val stringPrism = new Prism[Either[String, Int], String] {
    def getOption(s: Either[String, Int]): Option[String] = s.fold(e => Some(e), _ => None)

    def reverseGet(a: String): Either[String, Int] = Left(a)
  }

  def modifyStringError(e: Either[String, Int]): Either[String, Int] =
    stringPrism.modify(err => s"Error occurred: $err")(e)

  def modifyStringError(e: Either[String, Int], errStack: List[String]): Either[String, Int] =
    stringPrism.modify(err => s"Errors occurred: $err ${errStack.mkString(" ")}")(e)

}
