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

package util.alg.mod

import util.alg.mod.Modular.{Mods, NToChar}

import scala.annotation.tailrec


object Mod {
  def fromString(s: String, modulus: Int): Option[Mod] = {
    for {
      base <- Some(modulus).filter(Mods.contains)
      validSymbols = NToChar.filter(_._1 < base).values.toSet
      repr <- Some(s).filter(_.forall(validSymbols.contains))
    } yield Modular(repr, base)
  }

  def fromInt(i: Int, modulus: Int): Mod = {
    @tailrec
    def mkRepr(n: Int, acc: String = ""): String = {
      if (n <= 0) acc.reverse
      else mkRepr(n / modulus, acc + (n % modulus))
    }
    Modular(mkRepr(i), modulus)
  }
}
