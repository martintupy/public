package slides.stream

import java.net.URL

object UrlExtractor {

  val partMatch = "merge_requests"

  def urlPath(url: String): String = new URL(url).getPath

  def pathProjectName(path: String): Option[String] = {
    val parts = path.split("/")

    Some(parts.takeWhile(_ != partMatch))
      .filter(_.length != parts.length)
      .flatMap(_.lastOption)
  }

  def urlProjectName(s: String): Option[String] = pathProjectName(urlPath(s))

  def urlProjectNames(urls: List[String]): List[String] = {
    urls.map { url =>
      val path = urlPath(url)
      pathProjectName(path)
    }.collect { case Some(url) => url }
  }

}
