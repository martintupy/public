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

trait Iso[S, A] { self =>
  def get(s: S): A

  def reverseGet(a: A): S

  def set(a: A)(s: S): S = reverseGet(a)

  def modify(f: A => A)(s: S): S = reverseGet(f(get(s)))

  def reverse: Iso[A, S] = new Iso[A, S] {
    def get(a: A): S = self.reverseGet(a)

    def reverseGet(s: S): A = self.get(s)
  }

  def composeIso[B](other: Iso[A, B]): Iso[S, B] = new Iso[S, B] {
    def get(s: S): B = other.get(self.get(s))

    def reverseGet(b: B): S = self.reverseGet(other.reverseGet(b))
  }

  def composeLens[B](lens: Lens[A, B]): Lens[S, B] = asLens composeLens lens

  def composePrism[B](prism: Prism[A, B]): Prism[S, B] = ???

  def composeOptional[B](optional: Optional[A, B]): Optional[S, B] = ???

  def asLens: Lens[S, A] = new Lens[S, A] {
    def get(s: S): A = self.get(s)

    def set(a: A)(s: S): S = self.reverseGet(a)
  }

  def asPrism: Prism[S, A] = ???

  def asOptional: Optional[S, A] = ???
}
