package slides.optics.prism

import slides.optics.optional.Optional

trait Prism[S, A] { self =>
  def getOption(s: S): Option[A]

  def reverseGet(a: A): S

  def modify(f: A => A)(s: S): S = getOption(s).map(a => reverseGet(f(a))).getOrElse(s)

  def composePrism[B](other: Prism[A, B]): Prism[S, B] = new Prism[S, B] {
    def getOption(s: S): Option[B] = self.getOption(s).flatMap(a => other.getOption(a))

    def reverseGet(b: B): S = self.reverseGet(other.reverseGet(b))
  }

  def composeOptional[B](optional: Optional[A, B]) = asOptional composeOptional optional

  def asOptional: Optional[S, A] = new Optional[S, A] {
    def getOption(s: S): Option[A] = self.getOption(s)

    def set(a: A)(s: S): S = self.reverseGet(a)
  }

}
