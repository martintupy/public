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

package exercise.optics.exercise

import monocle.{Iso, Prism}
import util.alg.RomanNumber
import util.alg.mod.{Mod, Octal}


object Arithmetic extends App {

  // --------------- Octal number ----------------
  val number = "43123"

  def flip(mod: Mod): Mod = mod.flip

  def flipOctal(s: String): String = ???

  val flippedResult = "34654"

  val invalid = "901a"

  assert(flipOctal(number) == flippedResult)
  assert(flipOctal(invalid) == invalid)

  // -------------- Roman number -----------------
  val age = 47
  val bornYear = "MCMLXXII"
  val invalidRoman = "yay"

  def subtractFromRoman(s: String, n: Int): Option[Int] = ???

  assert(subtractFromRoman(bornYear, age) == Option(2019))
  assert(subtractFromRoman(invalidRoman, age) == None)






}
