package binary_ops.group


import binary_ops.semigroup.Semigroup

import scala.concurrent.duration.{Duration, FiniteDuration}

object GroupExamples {

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

  implicit val finiteDurationGroup = new Group[FiniteDuration] {
    def inverse(a: FiniteDuration): FiniteDuration = -a

    def empty: FiniteDuration = Duration.Zero

    def combine(x: FiniteDuration, y: FiniteDuration): FiniteDuration = x - y
  }
  // map example
  import binary_ops.semigroup.SemigroupExample._

  implicit def mapGroup[K, V: Group]: Group[Map[K, V]] = new Group[Map[K, V]] {
    def inverse(a: Map[K, V]): Map[K, V] =
      a.view.mapValues(Group[V].inverse).toMap.filterNot { case (_, v) => Group[V].isEmpty(v) }

    def empty: Map[K, V] = Map.empty

    def combine(x: Map[K, V], y: Map[K, V]): Map[K, V] =
      Semigroup[Map[K, V]].combine(x, y).filterNot { case (_, v) => Group[V].isEmpty(v) }
  }
}
