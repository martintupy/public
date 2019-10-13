package optics.prism

object PrismMotivation extends App {
  {
    def modifyStringError(e: Either[String, Int]): Either[String, Int] = {
      e match {
        case Left(s) => Left(s"Error occurred: $s")
        case Right(_) => e
      }
    }

    println(modifyStringError(Left("Wrong side")))
    // Left(Error occurred: Wrong side)
    println(modifyStringError(Right(23)))
    // Right(23)
  }

  {
    def modifyStringError(e: Either[String, Int], errStack: List[String]): Either[String, Int] = {
      e match {
        case Left(s) => Left(s"Errors occurred: $s ${errStack.mkString(" ")}")
        case Right(_) => e
      }
    }

    println(modifyStringError(Left("Wrong side"), List("Error1", "Error2")))
    // Left(Errors occurred: Wrong side Error1 Error2)
    println(modifyStringError(Right(23), List.empty))
    // Right(23)
  }




}
