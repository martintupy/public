package slides.types

object Product extends App {


  case class Person(firstName: String, secondName: String)

  case class AnyProduct(
                           a1: Any,
                           a2: Any,
                           a3: Any,
                           a4: Any,
                           a5: Any,
                           a6: Any,
                           a7: Any,
                           a8: Any,
                           a9: Any,
                           a10: Any,
                           a11: Any,
                           a12: Any,
                           a13: Any,
                           a14: Any,
                           a15: Any,
                           a16: Any,
                           a17: Any,
                           a18: Any,
                           a19: Any,
                           a20: Any,
                           a21: Any,
                           a22: Any,
                           a23: Any,
                           a24: Any,
                           a25: Any,
                           a26: Any,
                         )


  val product = AnyProduct(
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
  )

//  val AnyProduct(
//    a1,
//    a2,
//    a3,
//    a4,
//    a5,
//    a6,
//    a7,
//    a8,
//    a9,
//    a10,
//    a11,
//    a12,
//    a13,
//    a14,
//    a15,
//    a16,
//    a17,
//    a18,
//    a19,
//    a20,
//    a21,
//    a22,
//    a23,
//    a24,
//    a25,
//    a26
//  ) = product

  val res = product match {
    case AnyProduct(
    a1,
    a2,
    a3,
    a4,
    a5,
    a6,
    a7,
    a8,
    a9,
    a10,
    a11,
    a12,
    a13,
    a14,
    a15,
    a16,
    a17,
    a18,
    a19,
    a20,
    a21,
    a22,
    a23,
    a24,
    a25,
    a26
    ) => a26
  }


  println(res)
  println(product)

  def anyMethod(
                 a1: Any,
                 a2: Any,
                 a3: Any,
                 a4: Any,
                 a5: Any,
                 a6: Any,
                 a7: Any,
                 a8: Any,
                 a9: Any,
                 a10: Any,
                 a11: Any,
                 a12: Any,
                 a13: Any,
                 a14: Any,
                 a15: Any,
                 a16: Any,
                 a17: Any,
                 a18: Any,
                 a19: Any,
                 a20: Any,
                 a21: Any,
                 a22: Any,
                 a23: Any,
                 a24: Any,
                 a25: Any,
                 a26: Any,
               ):  Any = a26

}
