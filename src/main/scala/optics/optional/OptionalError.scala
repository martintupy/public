package optics.optional

object OptionalError {

  sealed trait Error
  case class ArithmeticError(arg1: Int, arg2: Int) extends Error
  case class SyntaxError(message: String) extends Error
  case class InvalidArgument(message: String, n: Int) extends Error

  val errorString = new Optional[Error, String] {
    def getOption(err: Error): Option[String] = err match {
      case InvalidArgument(message, _) => Some(message)
      case SyntaxError(message) => Some(message)
      case _ => None
    }

    def set(a: String)(err: Error): Error = err match {
      case SyntaxError(_) => SyntaxError(a)
      case InvalidArgument(_, n) => InvalidArgument(a, n)
      case e => e
    }
  }

  def modifyStringError(e: Error): Error =
    errorString.modify(message => s"Error occurred: ${message}")(e)

  def mkMessage(errs: List[String]) = s"Errors occurred: ${errs.mkString(", ")}"
  def modifyStringError(e: Error, errStack: List[String]): Error =
    errorString.modify(s => mkMessage(errStack :+ s))(e)
}
