package optics.optional

import monocle.Optional

object OptionalMonocle extends App {

  def first[A] = Optional[Seq[A], A](_.headOption)(a => {
    case _ :: tail => a :: tail
    case list => list
  })

  def second[A] = Optional[Seq[A], A](_.drop(1).headOption)(a => {
    case first :: _ :: tail => first :: a :: tail
    case list => list
  })

  def last[A] = Optional[Seq[A], A](_.lastOption)(a => {
    case Nil => Nil
    case list => list.dropRight(1) :+ a
  })

  println(last[Int].modify(_ * 10)(List(1, 2, 3)))
  // List(1, 2, 30)


  def nthOptional[A](n: Int) = Optional[Iterable[A], A](_.drop(n).headOption) { a =>list =>
    val (l, r) = list.splitAt(n)
    l ++ List(a) ++ r.drop(1)
  }

  println(nthOptional[Char](4).modify(_.toUpper)("jumpshot".toCharArray).mkString)
  println(nthOptional[Char](4).modify(_.toUpper)("yes".toCharArray).mkString)

  def keyOptional[K, V](k: K) =
    Optional[Map[K, V], V](_.get(k))(v => map => map.updated(k, v))
}
