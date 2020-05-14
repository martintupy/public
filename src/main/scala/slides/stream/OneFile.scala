package slides.stream

import java.nio.file.Paths

import cats.effect.{Blocker, ExitCode, IO, IOApp, Sync}
import fs2.Stream
import cats.implicits._

import scala.collection.SortedMap
import scala.collection.immutable.TreeMap

object OneFile extends IOApp {

  val path = Paths.get("/Users/martin.tupy/data/out_results.txt")

  def run(args: List[String]): IO[ExitCode] = {
    implicit val FSync = Sync[IO]

    Stream.resource(Blocker[IO]).flatMap { blocker =>
      fs2.io.file.readAll(path, blocker, 4096)
        .through(fs2.text.utf8Decode)
        .through(fs2.text.lines)
    }.compile
      .toList.map(_.groupBy(i => i).view.mapValues(_.size).toMap)
      .map(_.toList.sortBy(_._2))
      .map(println)
      .as(ExitCode.Success)
  }
}
