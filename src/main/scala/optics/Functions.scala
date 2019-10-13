package optics

import monocle.Lens
import monocle.function.At
import monocle.function.At._

object Functions extends App {

  implicit class MapSyntax[K, V](map: Map[K, V]) {
    def at(k: K): Lens[Map[K, V], Option[V]] = At.atMap[K, V].at(k)
  }

  val r = Map(1 -> "a").at(1).modify(_.map(_ + "!"))
  println(r)
}

