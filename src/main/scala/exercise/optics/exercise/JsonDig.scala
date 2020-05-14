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

import util.json._

object JsonDig extends App {

  val sampleJson: Json = JObj(Map(
    "table" -> JObj(Map(
      "name" -> JArr(Vector(JStr("Adam"), JStr("Anna"), JStr("Daniel"), JStr("Maria"), JStr("Michal"), JStr("Filip"), JStr("Lucia"), JStr("Hanna"))),
      "age" -> JArr(Vector(JNum(13), JNum(23), JNull, JNull, JNum(12), JNum(72), JNull, JNum(55))),
    )),
    "ageThreshold" -> JNum(24)
  ))

  /**
   * json of shape:
   * {
   *   "table" : {
   *     "name": [String]
   *     "age": [Number]
   *   }
   *   "ageThreshold": Number
   * }
   *
   * We don't like null, so we won't even consider such people
   */
  def oldEnoughNames(json: Json): Option[Vector[String]] = {
    ???
  }



  val result = Some(Vector("Adam", "Anna", "Michal"))

  assert(oldEnoughNames(sampleJson) == result)

}
