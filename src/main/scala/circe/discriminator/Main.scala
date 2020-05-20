package circe.discriminator
import circe.discriminator.Inner._
import circe.discriminator.Outer._
import io.circe.syntax._

object Main extends App {

  val innerA: Inner = InnerA(32, "a")
  val innerB: Inner = InnerB("bbb", "b")

  val outerA: Outer = OuterA(innerA)

  val outerB2: Outer = OuterB(innerA, 10)
  val outerB1: Outer = OuterB(innerA)
  val outerB3: Outer = OuterB(innerB, 10)

  val outerC1: Outer = OuterC(innerA, "outerC1")
  val outerC2: Outer = OuterC(innerB, "outerC2")


  println(innerA.asJson)

  println(outerB1.asJson)
  println(outerB2.asJson)
  println(outerB3.asJson)

  println(outerC1.asJson)
  println(outerC2.asJson)

  """
    |{
    |  "i" : 32,
    |  "name" : "a",
    |  "type" : "InnerA"
    |}
    |{
    |  "inner" : {
    |    "i" : 32,
    |    "name" : "a",
    |    "type" : "InnerA"
    |  },
    |  "num" : 0,
    |  "type" : "OuterB"
    |}
    |{
    |  "inner" : {
    |    "i" : 32,
    |    "name" : "a",
    |    "type" : "InnerA"
    |  },
    |  "num" : 10,
    |  "type" : "OuterB"
    |}
    |{
    |  "inner" : {
    |    "s" : "bbb",
    |    "name" : "b",
    |    "type" : "InnerB"
    |  },
    |  "num" : 10,
    |  "type" : "OuterB"
    |}
    |{
    |  "inner" : {
    |    "i" : 32,
    |    "name" : "a",
    |    "type" : "InnerA"
    |  },
    |  "name" : "outerC1",
    |  "type" : "OuterC"
    |}
    |{
    |  "inner" : {
    |    "s" : "bbb",
    |    "name" : "b",
    |    "type" : "InnerB"
    |  },
    |  "name" : "outerC2",
    |  "type" : "OuterC"
    |}""".stripMargin
}
