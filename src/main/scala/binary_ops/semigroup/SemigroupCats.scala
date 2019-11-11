package binary_ops.semigroup

object SemigroupCats extends App {

  import cats.Semigroup

  implicit def setIntersectionSemigroup[A] = Semigroup.instance[Set[A]]((x, y) => x intersect y)

  setIntersectionSemigroup.combine(Set(1, 2, 3), Set(2, 3, 4))

  implicitly[Semigroup[Set[Int]]].combine(Set(1, 2, 3), Set(1, 2, 3))

  Semigroup[Set[Int]].combine(Set(1, 2, 3), Set(1, 2, 3))

  import cats.syntax.semigroup._

  Set(1, 2, 3) combine Set(2, 3, 4)

  Set(1, 2, 3) |+| Set(2, 3, 4)

  val sets = List(Set(1, 2, 3, 5), Set(2, 5), Set(2, 4, 5, 6), Set(2, 3))

  val totalUnion = sets.foldLeft(Set.empty[Int])(_ union _)

  sets.foldLeft(totalUnion)(_ |+| _)
  // Set(2)
  sets.foldRight(totalUnion)(_ |+| _)
  // Set(2)
}
