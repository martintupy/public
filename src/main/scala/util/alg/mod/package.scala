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

package util.alg

/**
 * https://en.wikipedia.org/wiki/List_of_numeral_systems#Standard_positional_numeral_systems
 */
package object mod {

  type Mod = Modular

  object Binary {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 2)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 2)
  }

  object Ternary {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 3)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 3)
  }

  object Quaternary {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 4)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 4)
  }

  object Quinary {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 5)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 5)
  }

  object Senary {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 6)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 6)
  }

  object Septenary {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 7)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 7)
  }

  object Octal {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 8)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 8)
  }

  object Nonary {
    def fromString(s: String): Option[Mod] = Mod.fromString(s, 9)
    def fromInt(i: Int): Mod = Mod.fromInt(i, 9)
  }
}
