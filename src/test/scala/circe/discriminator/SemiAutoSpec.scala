package circe.discriminator
import circe.discriminator.Inner.{InnerA, InnerB}
import circe.discriminator.Outer.{OuterA, OuterB, OuterC}
import io.circe.parser._
import io.circe.syntax._
import org.scalatest.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class SemiAutoSpec extends AnyFlatSpec with Matchers {

  "circe auto derivation for union types" should "encode to json" in {
    (InnerA(i = 32, name = "a"): Inner).asJson shouldEqual
      parse("""
        |{
        |  "i" : 32,
        |  "name" : "a",
        |  "type" : "InnerA"
        |}
        |""".stripMargin).getOrElse(throw new Exception("Wrong json"))

    (OuterA(inner = InnerA(i = 32, name = "a")): Outer).asJson shouldEqual
      parse("""
              |{
              |  "inner" : {
              |    "i" : 32,
              |    "name" : "a",
              |    "type" : "InnerA"
              |  },
              |  "type" : "OuterA"
              |}
              |""".stripMargin).getOrElse(throw new Exception("Wrong json"))

    (OuterB(inner = InnerA(i = 32, name = "a")): Outer).asJson shouldEqual
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

    (OuterB(inner = InnerA(i = 32, name = "a"), num = 10): Outer).asJson shouldEqual
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

    (OuterB(inner = InnerB(s = "bbb", name = "b"), num = 10): Outer).asJson shouldEqual
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

    (OuterC(inner = InnerA(i = 32, name = "a"), name = "outerC1"): Outer).asJson shouldEqual
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

    (OuterC(inner = InnerB(s = "bbb", name = "b"), name = "outerC2"): Outer).asJson shouldEqual
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
