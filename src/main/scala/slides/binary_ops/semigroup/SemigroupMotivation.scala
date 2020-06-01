package slides.binary_ops.semigroup

object SemigroupMotivation {

  def sumInt(x: Int, y: Int): Int = x + y

  def sumDouble(x: Double, y: Double): Double = x + y

  def sumLong(x: Long, y: Long): Long = x + y

  def concatString(x: String, y: String): String = x ++ y

  def concatIntList(x: List[Int], y: List[Int]): List[Int] = x ++ y

  def intSetUnion(x: Set[Int], y: Set[Int]): Set[Int] = x union y

  def setUnion[A](x: Set[A], y: Set[A]): Set[A] = x union y

  def concatList[A](x: List[A], y: List[A]): List[A] = x ++ y


  def connect[A](x: A, y: A): A = ???

  {
    def connect[A](x: A, y: A)(implicit semigroup: Semigroup[A]): A = semigroup.combine(x, y)

    import SemigroupExample._

    def sumInt(x: Int, y: Int): Int = connect(x, y)

    def sumDouble(x: Double, y: Double): Double = connect(x, y)

    def sumLong(x: Long, y: Long): Long = connect(x, y)

    def concatString(x: String, y: String): String = connect(x, y)

    def concatIntList(x: List[Int], y: List[Int]): List[Int] = connect(x, y)

    def setIntUnion(x: Set[Int], y: Set[Int]): Set[Int] = connect(x, y)

    def concatList[A](x: List[A], y: List[A]): List[A] = connect(x, y)

    def setUnion[A](x: Set[A], y: Set[A]): Set[A] = connect(x, y)
  }

}
