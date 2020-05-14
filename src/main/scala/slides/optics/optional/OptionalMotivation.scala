package slides.optics.optional

object OptionalMotivation extends App {

  sealed trait Error
  case class ArithmeticError(arg1: Int, arg2: Int) extends Error
  case class SyntaxError(message: String) extends Error
  case class InvalidArgument(message: String, n: Int) extends Error


  {
    def modifyStringError(e: Error): Error = {
      e match {
        case SyntaxError(message) => SyntaxError(s"Error occurred: ${message}")
        case InvalidArgument(message, n) => InvalidArgument(s"Error occurred: ${message}", n)
        case _ => e
      }
    }

    println(modifyStringError(SyntaxError("Do not conform")))
    // SyntaxError(Error occurred: Do not conform)
    println(modifyStringError(ArithmeticError(1, 0)))
    // ArithmeticError(1, 0)
  }

  {
    def mkMessage(errs: List[String]) = s"Errors occurred: ${errs.mkString(", ")}"

    def modifyStringError(e: Error, errStack: List[String]): Error = {
      e match {
        case SyntaxError(message) =>
          SyntaxError(mkMessage(errStack :+ message))
        case InvalidArgument(message, n) =>
          InvalidArgument(mkMessage(errStack :+ message), n)
        case _ => e
      }
    }
    println(modifyStringError(SyntaxError("Do not conform"), List("Error1", "Error2")))
    // SyntaxError(Errors occurred: Error1, Error2, Do not conform)
  }




}
