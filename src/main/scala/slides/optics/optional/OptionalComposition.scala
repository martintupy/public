package slides.optics.optional

import slides.optics.prism.Prism

object OptionalComposition {

  sealed trait Error
  case class ArithmeticError(arg1: Int, arg2: Int) extends Error
  case class SyntaxError(message: String) extends Error
  case class InvalidArgument(message: String, n: Int) extends Error


  {
    def modifyStringError(e: Either[Error, Int]): Either[Error, Int] = {
      e match {
        case Left(SyntaxError(message)) => Left(SyntaxError(s"Error occurred: ${message}"))
        case Left(InvalidArgument(message, n)) => Left(InvalidArgument(s"Error occurred: ${message}", n))
        case _ => e
      }
    }

    println(modifyStringError(Left(SyntaxError("Do not conform"))))
    // Left(SyntaxError(Error occurred: Do not conform))
    println(modifyStringError(Right(23)))
    // Right(23)
  }

  {
    def mkMessage(errs: List[String]) = s"Errors occurred: ${errs.mkString(", ")}"

    def modifyStringError(e: Either[Error, Int], errStack: List[String]): Either[Error, Int] = {
      e match {
        case Left(SyntaxError(message)) =>
          Left(SyntaxError(mkMessage(errStack :+ message)))
        case Left(InvalidArgument(message, n)) =>
          Left(InvalidArgument(mkMessage(errStack :+ message), n))
        case _ => e
      }
    }
    println(modifyStringError(Left(SyntaxError("Do not conform")), List("Error1", "Error2")))
    // Left(SyntaxError(Errors occurred: Error1, Error2, Do not conform))
  }

  {
    val eitherError = new Prism[Either[Error, Int], Error] {
      def getOption(e: Either[Error, Int]): Option[Error] = e match {
        case Left(error) => Some(error)
        case _ => None
      }

      def reverseGet(a: Error): Either[Error, Int] = Left(a)
    }

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

    def modifyErrorString(e: Either[Error, Int]): Either[Error, Int] = {
      (eitherError composeOptional errorString).modify(message => s"Error occurred: ${message}")(e)
    }
    def mkMessage(errs: List[String]) = s"Errors occurred: ${errs.mkString(", ")}"
    def modifyErrorString2(e: Either[Error, Int], errStack: List[String]): Either[Error, Int] = {
      (eitherError composeOptional errorString).modify(s => mkMessage(errStack :+ s))(e)
    }
  }

}
