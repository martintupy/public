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

import org.scalatest._

class LensSpec extends FlatSpec with Matchers {
  case class Street(name: String, num: Int)
  val streetName = new Lens[Street, String] {
    def get(s: Street): String = s.name

    def set(a: String)(s: Street): Street = s.copy(name = a)
  }
  val street = Street("Hlavni", 22)

  "What you get" should "be what you set" in {
    def getSet[S, A](l: Lens[S, A], s: S): Boolean = l.set(l.get(s))(s) == s
    getSet(streetName, street) shouldEqual true
  }

  "What you set" should "be what you get" in {
    def setGet[S, A](l: Lens[S, A], s: S, a: A): Boolean = l.get(l.set(a)(s)) == a
    setGet(streetName, street, "Krizikova") shouldEqual true
  }

}
