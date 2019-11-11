package binary_ops.monoid

import binary_ops.semigroup.Semigroup

trait Monoid[A] extends Semigroup[A] {
  def empty: A

  def combineAll(as: Seq[A]): A =
    as.foldLeft(empty)(combine)

  def isEmpty(a: A): Boolean = a == empty
}
