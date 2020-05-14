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

trait Lens[S, A] { self =>
  def get(s: S): A

  def set(a: A)(s: S): S

  def modify(f: A => A)(s: S): S = set(f(get(s)))(s)

  def composeIso[B](iso: Iso[A, B]): Lens[S, B] = self composeLens iso.asLens

  def composeLens[B](other: Lens[A, B]): Lens[S, B] = new Lens[S, B] {
    def get(s: S): B = other.get(self.get(s))

    def set(b: B)(s: S): S = self.set(other.set(b)(self.get(s)))(s)
  }

  def composePrism[B](prism: Prism[A, B]): Optional[S, B] = ???

  def composeOptional[B](optional: Optional[A, B]): Optional[S, B] = ???

  def asOptional: Optional[S, A] = ???
}
