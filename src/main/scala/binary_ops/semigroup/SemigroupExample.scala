package binary_ops.semigroup

object SemigroupExample {
  implicit val intAdditionSemigroup = new Semigroup[Int] {
    def combine(x: Int, y: Int): Int = x + y
  }

  implicit val doubleAdditionSemigroup = new Semigroup[Double] {
    def combine(x: Double, y: Double): Double = x + y
  }

  implicit val longAdditionSemigroup = new Semigroup[Long]{
    def combine(x: Long, y: Long): Long = x + y
  }

  implicit def listConcatSemigroup[A] = new Semigroup[List[A]] {
    def combine(x: List[A], y: List[A]): List[A] = x ++ y
  }

  implicit val stringConcatSemigroup = new Semigroup[String] {
    def combine(x: String, y: String): String = x ++ y
  }

  implicit def setUnionSemigroup[A] = new Semigroup[Set[A]] {
    def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }


  // more examples

  def numericMultiplicationSemigroup[N: Numeric] = new Semigroup[N] {
    def combine(x: N, y: N): N = Numeric[N].times(x, y)
  }

  def numericAdditionSemigroup[N: Numeric] = new Semigroup[N] {
    def combine(x: N, y: N): N = Numeric[N].plus(x, y)
  }

  implicit def mapMergeSemigroup[K, V](implicit semigroup: Semigroup[V]): Semigroup[Map[K, V]] = new Semigroup[Map[K, V]] {
    def combine(x: Map[K, V], y: Map[K, V]): Map[K, V] = {
      x.foldLeft(y) { case (xAcc, (yKey, yValue)) =>
        val value = Semigroup.maybeCombine(yValue, xAcc.get(yKey))
        xAcc.updated(yKey, value)
      }
    }
  }

  def connect[A](x: A, y: A)(implicit semigroup: Semigroup[A]): A = semigroup.combine(x, y)

  connect(1, 1)
  // 2
  connect(1.0, 1.0)
  // 2.0
  connect(1L, 1L)
  // 2L
  connect(List(1, 2, 3), List(4, 5, 6))
  // List(1, 2, 3, 4, 5, 6)
  connect("abc", "def")
  // abcdef
}
