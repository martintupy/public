package slides.binary_ops.monoid

import slides.binary_ops.semigroup.Semigroup

trait Monoid[A] extends Semigroup[A] {
  def empty: A

  def combineAll(as: Seq[A]): A =
    as.foldLeft(empty)(combine)

  def isEmpty(a: A): Boolean = a == empty
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
}