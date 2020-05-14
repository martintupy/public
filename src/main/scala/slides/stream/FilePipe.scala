package slides.stream

import java.nio.file.Path

import cats.effect.{Blocker, ContextShift, Sync}
import fs2.io.file
import fs2.{Pipe, Stream, text}

case class FilePipe[F[_]: Sync: ContextShift](in: Path, out: Path) {

  def convert(pipe: Pipe[F, String, String]): Stream[F, Unit] = Stream.resource(Blocker[F]).flatMap { blocker =>
    file.readAll(in, blocker, 4096)
      .through(text.utf8Decode)
      .through(pipe)
      .through(text.utf8Encode)
      .through(file.writeAll(out, blocker))
  }

}
