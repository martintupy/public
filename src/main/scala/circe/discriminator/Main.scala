package circe.discriminator
import circe.discriminator.Inner._
import circe.discriminator.Outer._
import io.circe.syntax._

object Main extends App {

  val innerA: Inner = InnerA(i = 32, name = "a")
  val innerB: Inner = InnerB(s = "bbb", name = "b")

  val outerA: Outer = OuterA(inner = innerA)

  val outerB2: Outer = OuterB(inner = innerA, num = 10)
  val outerB1: Outer = OuterB(inner = innerA)
  val outerB3: Outer = OuterB(inner = innerB, num = 10)

  val outerC1: Outer = OuterC(inner = innerA, name = "outerC1")
  val outerC2: Outer = OuterC(inner = innerB, name = "outerC2")


  println(innerA.asJson)

  println(outerB1.asJson)
  println(outerB2.asJson)
  println(outerB3.asJson)

  println(outerC1.asJson)
  println(outerC2.asJson)

}
