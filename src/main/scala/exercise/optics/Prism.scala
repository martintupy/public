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

trait Prism[S, A] { self =>
  def getOption(s: S): Option[A]

  def reverseGet(a: A): S

  def modify(f: A => A)(s: S): S = getOption(s).map(a => reverseGet(f(a))).getOrElse(s)

  def composeIso[B](iso: Iso[A, B]): Prism[S, B] = ???

  def composeLens[B](lens: Lens[A, B]): Optional[S, B] = ???

  def composePrism[B](other: Prism[A, B]): Prism[S, B] = ???

  def asOptional: Optional[S, A] = ???
}
