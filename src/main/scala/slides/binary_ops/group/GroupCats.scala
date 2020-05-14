package slides.binary_ops.group

import cats.kernel.Eq

object GroupCats extends App {

  import cats.Group
  import cats.instances.option._
  import cats.kernel.Monoid

  implicit def optionGroup[A: Group] = new Group[Option[A]] {
    def inverse(a: Option[A]): Option[A] =
      a.map(Group[A].inverse).filterNot(a => Group[A].isEmpty(a)(Eq.fromUniversalEquals[A]))

    def empty: Option[A] = None

    def combine(x: Option[A], y: Option[A]): Option[A] =
      Monoid[Option[A]].combine(x, y).filterNot(a => Group[A].isEmpty(a)(Eq.fromUniversalEquals[A]))
  }

  import cats.instances.int._

  optionGroup.remove(Option(5), Option(1))

  Group[Option[Int]].remove(Option(5), Option(1))

  import cats.syntax.group._

  Option(5) remove Option(1)

  Option(5) |-| Option(1)

  Option(5).inverse


  Option.empty[Int] remove Some(5)

  import cats.syntax.option._
  none[Int] remove 5.some

  println(5.some remove 5.some) // None
  5.some remove none // Some(5)
  none[Int] remove 5.some // Some(-5)
  none[Int] remove none // None



}
