package slides.binary_ops.monoid

object MonoidExample extends App {

  implicit def numericAdditionMonoid[A: Numeric] = new Monoid[A] {

    def empty: A = Numeric[A].zero

    def combine(x: A, y: A): A = Numeric[A].plus(x, y)
  }

  implicit def listConcatMonoid[A] = new Monoid[List[A]] {

    def empty: List[A] = List.empty

    def combine(x: List[A], y: List[A]): List[A] = x ++ y
  }

  implicit def stringConcatMonoid = new Monoid[String] {

    def empty: String = ""

    def combine(x: String, y: String): String = x ++ y
  }

  implicit def setUnionMonoid[A] = new Monoid[Set[A]] {
    def empty: Set[A] = Set.empty

    def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

  def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = monoid.combineAll(as)

  connectAll(List(1, 2, 0, 10, 11))
  // 24
  connectAll(List(2.5, 10.0, 7.25))
  // 19.75
  connectAll(List(List(1, 2, 3), List(4, 10, 11), List(0, 1, 2)))
  // List(1, 2, 3, 4, 10, 11, 0, 1, 2)
  connectAll(List(Set(1, 2, 3), Set.empty[Int], Set(2, 3, 10), Set(1)))
  // Set(1, 2, 3, 10)
  connectAll(List("Hey", " ", "you ", "there", "!"))
  // Hey you there!
}
