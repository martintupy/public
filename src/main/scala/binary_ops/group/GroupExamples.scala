package binary_ops.group


import cats.Eq
import cats.implicits._
import cats.kernel.Group
import monocle.Iso

import scala.concurrent.duration.{Duration, FiniteDuration}

object GroupExamples extends App {

  {
    implicit val intGroup = new Group[Int] {
      def inverse(a: Int): Int = -a

      def empty: Int = 0

      def combine(x: Int, y: Int): Int = x + y
    }

    implicit val longGroup = new Group[Long] {
      def inverse(a: Long): Long = -a

      def empty: Long = 0L

      def combine(x: Long, y: Long): Long = x + y
    }

    implicit val doubleGroup = new Group[Double] {
      def inverse(a: Double): Double = -a

      def empty: Double = 0.0

      def combine(x: Double, y: Double): Double = x + y
    }

    // more examples
    implicit def numericGroup[A: Numeric] = new Group[A] {
      def inverse(a: A): A = Numeric[A].negate(a)

      def empty: A = Numeric[A].zero

      def combine(x: A, y: A): A = Numeric[A].plus(x, y)
    }
  }

  implicit val finiteDurationGroup = new Group[FiniteDuration] {
    def inverse(a: FiniteDuration): FiniteDuration = -a

    def empty: FiniteDuration = Duration.Zero

    def combine(x: FiniteDuration, y: FiniteDuration): FiniteDuration = x - y
  }
  // map example

  implicit def mapUnionGroup[K, V: Group: Eq]: Group[Map[K, V]] = new Group[Map[K, V]] {
    def inverse(a: Map[K, V]): Map[K, V] =
      a.view.mapValues(Group[V].inverse).toMap.filterNot { case (_, v) => Group[V].isEmpty(v) }

    def empty: Map[K, V] = Map.empty

    def combine(x: Map[K, V], y: Map[K, V]): Map[K, V] = {
      cats.instances.map.catsKernelStdMonoidForMap[K, V].combine(x, y)
        .filterNot { case (_, v) => Group[V].isEmpty(v) }
    }
  }

  def mapSetIso[K, V] = Iso[Map[K, V], Set[(K, V)]](_.toSet)(_.toMap)

  implicit def setUnionGroup[A, K, V: Group: Eq](implicit keyValueIso: Iso[A, (K, V)]): Group[Set[A]] = new Group[Set[A]] {
    def inverse(as: Set[A]): Set[A] =
      Group[Map[K, V]].inverse(as.map(keyValueIso.get).toMap)
        .map(keyValueIso.reverseGet _).toSet

    def empty: Set[A] = Set.empty[A]

    def combine(x: Set[A], y: Set[A]): Set[A] = {
      val xMap = x.map(keyValueIso.get).toMap
      val yMap = y.map(keyValueIso.get).toMap
      Group[Map[K, V]].combine(xMap, yMap).map(keyValueIso.reverseGet _).toSet
    }
  }

  val map1 = Map(
    "domain1.com" -> 1,
    "domain2.com" -> 3,
    "domain3.com" -> 2,
    "domain4.com" -> 1
  )

  val map2 = Map(
    "domain2.com" -> 22,
    "domain4.com" -> 10
  )

  val map3 = Map(
    "domain3.com" -> 20,
    "domain4.com" -> 31,
    "domain5.com" -> 32,
    "domain6.com" -> 2,
    "domain7.com" -> 17
  )

  val map4 = Map(
    "domain1.com" -> 5,
    "domain2.com" -> 3,
    "domain6.com" -> 12
  )


  val visits = List(map1, map2, map3, map4)

  val mapTotalBigger: Map[String, Int] =
    Map(
      "domain1.com" -> 26,
      "domain2.com" -> 31,
      "domain3.com" -> 25,
      "domain4.com" -> 42,
      "domain5.com" -> 32,
      "domain6.com" -> 14,
      "domain7.com" -> 17
    )

  val mapTotalSmaller: Map[String, Int] = Map(
    "domain1.com" -> 20,
    "domain2.com" -> 3,
    "domain3.com" -> 3
  )

  {
    import cats.instances.int._

    val visits = List(map1, map2, map3, map4)
    cats.instances.map.catsKernelStdMonoidForMap[String, Int].combineAll(visits).toList.sortBy(_._1) foreach println

    Group[Map[String, Int]].remove(mapTotalBigger, mapTotalSmaller).toList.sortBy(_._1) foreach println

    cats.instances.map.catsKernelStdMonoidForMap[String, Int].combineAll(visits) == Group[Map[String, Int]].remove(mapTotalBigger, mapTotalSmaller)
  }

  val maps1: Map[Int, Map[String, Int]] = Map(
    1 -> Map("Adam" -> 10, "Monika" -> 3, "Anna" -> 6             ),
    2 -> Map("Adam" -> 2,  "Monika" -> 1             ,"Maria" -> 5),
    3 -> Map(              "Monika" -> 3                          ),
  )

  val maps2 = Map(
    1 -> Map("Adam" -> 10,                "Anna" -> 3             ),
    2 -> Map("Adam" -> 3                                          ),
    3 -> Map("Adam" -> 4,  "Monika" -> 1,             "Maria" -> 3),
  )

  println(Group[Map[Int, Map[String, Int]]].remove(maps1, maps2))


}
