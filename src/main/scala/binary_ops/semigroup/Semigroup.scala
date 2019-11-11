package binary_ops.semigroup

trait Semigroup[A] {
  def combine(x: A, y: A): A

  def optionCombine(x: A, yOpt: Option[A]): A = {
    yOpt.map(y => combine(x, y)).getOrElse(x)
  }
}

object Semigroup {
  def apply[A](implicit semigroup: Semigroup[A]) = semigroup
}
