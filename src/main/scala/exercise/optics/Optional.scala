/*
 * Copyright 2018-2019 Martin Tupý
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package exercise.optics

trait Optional[S, A] { self =>

  def getOption(s: S): Option[A]

  def set(a: A)(s: S): S

  def modify(f: A => A)(s: S): S = getOption(s).map(a => set(f(a))(s)).getOrElse(s)

  def composeIso[B](iso: Iso[A, B]): Optional[S, B] = ???

  def composeLens[B](lens: Lens[A, B]): Optional[S, B] = ???

  def composePrism[B](prism: Prism[A, B]): Optional[S, B] = ???

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
