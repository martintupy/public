package slides.stream

import java.nio.file.Paths

import cats.effect.{ContextShift, ExitCode, IO, IOApp, Sync}
import cats.implicits._
import fs2.Stream

object TwoFiles extends IOApp {

  val in = Paths.get("/Users/martin.tupy/data/in.txt")
  val out = Paths.get("/Users/martin.tupy/data/out.txt")
  val outResults = Paths.get("/Users/martin.tupy/data/out_results.txt")

  def converter[F[_]: Sync: ContextShift]: Stream[F, Unit] = FilePipe(in, outResults).convert { stream =>
    stream
      .through(Util.urlRegexPipe)
      .mapFilter(UrlExtractor.urlProjectName)
      .through(Util.showPipe)
      .intersperse("\n")
  }

  def run(args: List[String]): IO[ExitCode] = {
    implicit val IOSync = Sync[IO]
    converter.compile.drain.as(ExitCode.Success)
  }
}
