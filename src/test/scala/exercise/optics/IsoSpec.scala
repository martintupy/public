package exercise.optics

import cats.implicits._
import monocle.law.discipline.IsoTests
import org.scalatest.Matchers
import org.scalatest.flatspec.AnyFlatSpec
import org.typelevel.discipline.scalatest.Discipline

class IsoSpec extends AnyFlatSpec with Discipline with Matchers {

  val listVectorIso: Iso[List[Int], Vector[Int]] = new Iso[List[Int], Vector[Int]]{
    def get(a: List[Int]): Vector[Int] = a.toVector
    def reverseGet(a: Vector[Int]): List[Int] = a.toList
  }
  val stringIntIso: Iso[String, Int] = new Iso[String, Int]{
    def get(a: String): Int = a.toInt
    def reverseGet(a: Int): String = a.toString
  } // not valid iso

  "List/Vector Round trip one way" should "end on start" in {
    def roundTripOneWay[S, A](i: Iso[S, A], s: S): Boolean = i.reverseGet(i.get(s)) == s
    roundTripOneWay(listVectorIso, List(1, 2, 3)) shouldBe true
  }

  "List/Vector Round trip the other way" should "start on end" in {
    def roundTripOtherWay[S, A](i: Iso[S, A], a: A): Boolean = i.get(i.reverseGet(a)) == a
    roundTripOtherWay(listVectorIso, Vector(1, 2, 3)) shouldBe true
  }

  "String/Int Round trip one way" should "end on start" in {
    def roundTripOneWay[S, A](i: Iso[S, A], s: S): Boolean = i.reverseGet(i.get(s)) == s
    roundTripOneWay(stringIntIso, "005") shouldBe false
  }

  checkAll("Id isotest", IsoTests[Int, Int](monocle.Iso.id[Int]))

}
