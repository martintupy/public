package slides.stream

import cats.Show
import cats.effect.Sync
import cats.implicits._
import fs2.{Pipe, Stream}

object Util {

  val urlRegex = """(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"""

  def urlRegexPipe[F[_]: Sync] = regexPipe(urlRegex)

  def regexPipe[F[_]: Sync](regex: String): Pipe[F, String, String] = { stream =>
    stream.flatMap(s => Stream.fromIterator[F](regex.r.findAllIn(s).iterator))
  }

  def showPipe[F[_]: Sync, O: Show]: Pipe[F, O, O] = stream => stream.showLinesStdOut *> stream

}
