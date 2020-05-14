package slides.binary_ops.semigroup

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

object Semigroup {
  def apply[A](implicit semigroup: Semigroup[A]) = semigroup

  def maybeCombine[A](xOpt: Option[A], y: A)(implicit semigroup: Semigroup[A]): A =
    xOpt.fold(y)(x => semigroup.combine(x, y))

  def maybeCombine[A](x: A, yOpt: Option[A])(implicit semigroup: Semigroup[A]): A =
    yOpt.fold(x)(y => semigroup.combine(x, y))
}
