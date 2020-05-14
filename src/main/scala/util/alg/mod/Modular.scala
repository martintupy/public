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

private[mod] trait Modular {

  def repr: String

  def modulus: Int

  def toInt: Int = repr.reverse.zipWithIndex.foldLeft(0) { case (acc, (c, n)) =>
    acc + (Modular.CharToN(c) * math.pow(modulus, n).toInt)
  }

  /**
   * mod + mod.flip + 1 == 0
   */
  def flip: Modular = {
    val flipped: String = repr.map { n => Modular.NToChar(modulus - 1 - Modular.CharToN(n)) }
    Modular(flipped, modulus)
  }

  override def toString: String = s"Mod($repr,$modulus)"
}

private [mod] object Modular {

  def apply(_repr: String, _modulus: Int): Modular = new Modular {
    def repr: String = normalizeRepr(_repr)
    def modulus: Int = _modulus
  }

  def normalizeRepr(repr: String): String = repr.dropWhile(_ == '0')
  val Mods = (2 to 36).toSet
  val Numerals: Seq[Int] = 0 to 35
  val Symbols: Seq[Char] = (0 to 9).map(n => (n + '0').toChar) ++ (10 to 35).map(n => (n + 'A' - 10).toChar)
  val NToChar: Map[Int, Char] = (Numerals zip Symbols).toMap
  val CharToN: Map[Char, Int] = NToChar.map { case (a, b) => (b, a) }
}