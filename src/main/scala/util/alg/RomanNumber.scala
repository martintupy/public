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

sealed abstract class RomanNumber(val repr: String) {
  def toInt: Int = RomanNumber.makeInt(repr)

  override def toString: String = s"RomaNumber($repr)"
}

object RomanNumber {

  def fromString(s: String): Option[RomanNumber] = {
    Some(s).filter(_.forall(SimpleDigits.keySet.contains)).map(apply)
  }


  def fromInt(number: Int): RomanNumber = {
    val repr = ComposedDigits.foldLeft(number -> "") { case ((num, acc), (romanDigit, digit)) =>
      if (num == 0) (num, acc)
      else (num % digit, acc + (romanDigit * (num / digit)))
    }._2
    RomanNumber(repr)
  }

  private def apply(repr: String): RomanNumber = new RomanNumber(repr){}

  private val ComposedDigits = List(
    "M" -> 1000,
    "CM" -> 900,
    "D" -> 500,
    "CD" -> 400,
    "C" -> 100,
    "XC" -> 90,
    "L" -> 50,
    "XL" -> 40,
    "X" -> 10,
    "IX" -> 9,
    "V" -> 5,
    "IV" -> 4,
    "I" -> 1
  )
  private val SimpleDigits = Map(
    'I' -> 1,
    'V' -> 5,
    'X' -> 10,
    'L' -> 50,
    'C' -> 100,
    'D' -> 500,
    'M' -> 1000
  )

  private def makeInt(repr: String): Int = {
    repr.toUpperCase.map(SimpleDigits).foldLeft[(Int, Int)]((0, 0)) { case ((total, last), curr) =>
      val reminder = if (last < curr) -2 * last else 0
      total + curr + reminder -> curr
    }._1
  }
}