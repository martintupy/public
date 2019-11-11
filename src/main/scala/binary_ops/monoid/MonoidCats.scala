package binary_ops.monoid


object MonoidCats extends App {

  import cats.Monoid
  import cats.Semigroup
  import cats.syntax.monoid._

  implicit def optionMonoid[A: Semigroup]: Monoid[Option[A]] = Monoid.instance(
    None,
    (xOpt, yOpt) => xOpt.fold(yOpt)(x => yOpt.fold(xOpt)(y => Some(x |+| y)))
  )

  val numberOptions = List(Some(1), None, Some(2), Some(23), Some(0), None)

  import cats.instances.int._

  numberOptions.foldLeft(optionMonoid.empty)(optionMonoid.combine)

  numberOptions.foldLeft(Monoid[Option[Int]].empty)(Monoid[Option[Int]].combine)

  numberOptions.foldLeft(Monoid[Option[Int]].empty)(_ combine _)

  numberOptions.foldLeft(Monoid[Option[Int]].empty)(_ |+| _)

  Monoid[Option[Int]].combineAll(numberOptions)
}
