package slides.binary_ops.group

import slides.binary_ops.monoid.Monoid

trait Group[A] extends Monoid[A] {

  def inverse(a: A): A

  def remove(x: A, y: A): A = combine(x, inverse(y))

}

object Group {
  def apply[A](implicit group: Group[A]) = group
}