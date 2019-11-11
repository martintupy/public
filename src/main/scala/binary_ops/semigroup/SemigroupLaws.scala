package binary_ops.semigroup

object SemigroupLaws extends App {

  import cats.implicits._

  val ints = List(1, 2, 3, 5, 7)
  assert(ints.foldLeft(0)(_ |+| _) == ints.foldRight(0)(_ |+| _))

  val strings = List("Hey", " ", "you", " ", "there")
  assert(strings.foldLeft("")(_ |+| _) == strings.foldRight("")(_ |+| _))

  val lists = List(List(1), List(1, 2, 3), List(), List(10, 0))
  assert(lists.foldLeft(List.empty[Int])(_ |+| _) == lists.foldRight(List.empty[Int])(_ |+| _))

  val sets = List(Set(1, 2, 3, 5), Set(2, 5), Set(2, 4, 5, 6), Set(2, 3))
  assert(sets.foldLeft(Set.empty[Int])(_ |+| _) == sets.foldRight(Set.empty[Int])(_ |+| _))
}
