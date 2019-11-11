package binary_ops.group

import binary_ops.monoid.Monoid

trait Group[A] extends Monoid[A] {

  def inverse(a: A): A

}

object Group {
  def apply[A](implicit group: Group[A]) = group
}