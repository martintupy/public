package slides.stream

import java.nio.file.{Files, Paths}

object RegexX extends App {


  val reg = "ab.*c".r

  val source = "aiasdifsjfpo uabsodfi sbodifbasiudfb asdpufb asdifsdfasdc"

  println(reg.findFirstIn(source))

  val in = "/Users/martin.tupy/data/text.txt"
  val out = "/Users/martin.tupy/data/out.txt"

  val string = Files.readAllLines(Paths.get("/Users/martin.tupy/data/text.txt")).toArray.mkString

  val url = "aahttps://git.int.jumpshot.com/engineering/funnel-fantasy/funnel-fantasy-service/merge_requests/409/diffs#diff-content-ffcc6ef6b1e4c17f61de4c859b8d22b19760ec8c as df"

  val urlRegex = """(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]""".r

  val results = urlRegex.findAllIn(string).mkString("\n")

  Files.write(Paths.get(out), results.getBytes)

  Array(1).prepended(2)

}
