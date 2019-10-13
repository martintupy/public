package optics.optional

trait Optional[S, A] { self =>

  def getOption(s: S): Option[A]

  def set(a: A)(s: S): S

  def modify(f: A => A)(s: S): S = getOption(s).map(a => set(f(a))(s)).getOrElse(s)

  def composeOptional[B](other: Optional[A, B]): Optional[S, B] = new Optional[S, B] {
    def getOption(s: S): Option[B] = self.getOption(s).flatMap(a => other.getOption(a))

    def set(b: B)(s: S): S = {
      val setOption = for {
        a <- self.getOption(s)
        b <- other.getOption(a)
      } yield self.set(other.set(b)(a))(s)
      setOption.getOrElse(s)
    }
  }
}