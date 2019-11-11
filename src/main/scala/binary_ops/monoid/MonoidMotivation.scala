package binary_ops.monoid

object MonoidMotivation {

  def sumInts(ints: List[Int]): Int = ints.sum

  def sumDoubles(doubles: List[Double]): Double = doubles.sum

  def sumLongs(longs: List[Long]): Long = longs.sum

  def concatIntLists(lists: List[List[Int]]): List[Int] = lists.foldLeft(List.empty[Int])(_ ++ _)

  def concatStrings(strings: List[String]) = strings.foldLeft("")(_ ++ _)

  def concatLists[A](lists: List[List[A]]): List[A] = lists.foldLeft(List.empty[A])(_ ++ _)

  def unionSets[A](sets: List[Set[A]]): Set[A] = sets.foldLeft(Set.empty[A])(_ union _)

  def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = ???

  import MonoidExample._
  {
    def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = as.foldLeft(monoid.empty)(monoid.combine)
    def sumInts(ints: List[Int]): Int = connectAll(ints)
    def sumDoubles(doubles: List[Double]): Double = connectAll(doubles)
    def sumLongs(longs: List[Long]): Long = connectAll(longs)
    def concatIntLists(lists: List[List[Int]]): List[Int] = connectAll(lists)
    def concatStrings(strings: List[String]): String = connectAll(strings)
    def concatLists[A](lists: List[List[A]]): List[A] = connectAll(lists)
    def unionSets[A](sets: List[Set[A]]): Set[A] = connectAll(sets)

  }
  {
    def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = monoid.combineAll(as)
  }


}
