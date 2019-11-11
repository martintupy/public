package binary_ops.semigroup

object SemigroupWrong {

  val semigroupForIntSubtract = new Semigroup[Int] {
    def combine(x: Int, y: Int): Int = x - y
  }

  def semigroupForSetDiff[A] = new Semigroup[Set[A]] {
    def combine(x: Set[A], y: Set[A]): Set[A] = x diff y
  }

//  List(1, 2, 3).foldLeft(0)

}
