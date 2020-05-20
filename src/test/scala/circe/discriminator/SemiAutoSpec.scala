package circe.discriminator
import circe.discriminator.Inner.{InnerA, InnerB}
import circe.discriminator.Outer.{OuterA, OuterB, OuterC}
import io.circe.parser._
import io.circe.syntax._
import org.scalatest.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class SemiAutoSpec extends AnyFlatSpec with Matchers {

  "circe auto derivation for union types" should "encode to json" in {
    val innerA: Inner = InnerA(32, "a")
    val innerB: Inner = InnerB("bbb", "b")

    val outerA: Outer = OuterA(innerA)

    val outerB2: Outer = OuterB(innerA, 10)
    val outerB1: Outer = OuterB(innerA)
    val outerB3: Outer = OuterB(innerB, 10)

    val outerC1: Outer = OuterC(innerA, "outerC1")
    val outerC2: Outer = OuterC(innerB, "outerC2")


    innerA.asJson shouldEqual
      parse("""
        |{
        |  "i" : 32,
        |  "name" : "a",
        |  "type" : "InnerA"
        |}
        |""".stripMargin).getOrElse(throw new Exception("Wrong json"))

    outerB1.asJson shouldEqual
      parse("""
        |{
        |  "inner" : {
        |    "i" : 32,
        |    "name" : "a",
        |    "type" : "InnerA"
        |  },
        |  "num" : 0,
        |  "type" : "OuterB"
        |}
        |""".stripMargin).getOrElse(throw new Exception("Wrong json"))

    outerB2.asJson shouldEqual
      parse("""
        |{
        |  "inner" : {
        |    "i" : 32,
        |    "name" : "a",
        |    "type" : "InnerA"
        |  },
        |  "num" : 10,
        |  "type" : "OuterB"
        |}
        |""".stripMargin).getOrElse(throw new Exception("Wrong json"))

    outerB3.asJson shouldEqual
      parse("""
        |{
        |  "inner" : {
        |    "s" : "bbb",
        |    "name" : "b",
        |    "type" : "InnerB"
        |  },
        |  "num" : 10,
        |  "type" : "OuterB"
        |}
        |""".stripMargin).getOrElse(throw new Exception("Wrong json"))

    outerC1.asJson shouldEqual
      parse("""
        |{
        |  "inner" : {
        |    "i" : 32,
        |    "name" : "a",
        |    "type" : "InnerA"
        |  },
        |  "name" : "outerC1",
        |  "type" : "OuterC"
        |}
        |""".stripMargin).getOrElse(throw new Exception("Wrong json"))

    outerC2.asJson shouldEqual
      parse("""
        |{
        |  "inner" : {
        |    "s" : "bbb",
        |    "name" : "b",
        |    "type" : "InnerB"
        |  },
        |  "name" : "outerC2",
        |  "type" : "OuterC"
        |}
        |""".stripMargin).getOrElse(throw new Exception("Wrong json"))
  }

}
