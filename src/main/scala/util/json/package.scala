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

package util

import monocle.Optional
import monocle.macros.{GenIso, GenPrism}

package object json {

  /** ----------- Json Prism ----------- */
  val jsonJNum = GenPrism[Json, JNum]
  val jsonJStr = GenPrism[Json, JStr]
  val jsonJBool = GenPrism[Json, JBool]
  val jsonJArr = GenPrism[Json, JArr]
  val jsonJObj = GenPrism[Json, JObj]
  val jsonJNull = GenPrism[Json, JNull.type]

  /** ----------- Iso ----------- */
  val jNumDouble = GenIso[JNum, Double]
  val jBoolBool = GenIso[JBool, Boolean]
  val jArrVector = GenIso[JArr, Vector[Json]]
  val jStrString = GenIso[JStr, String]
  val jObjMap = GenIso[JObj, Map[String, Json]]
  val jNullUnit = GenIso.unit[JNull.type]

  def jArrJson(n: Int): Optional[JArr, Json] = jArrVector composeOptional nth(n)

  def jObjJson(k: String): Optional[JObj, Json] = jObjMap composeOptional keyJson(k)

  /** ----------- Util ----------- */
  private def keyJson(k: String): Optional[Map[String, Json], Json] =
    Optional[Map[String, Json], Json](_.get(k))(v => map => map.updated(k, v))

  private def nth[A](n: Int) = Optional[Vector[A], A](_.drop(n).headOption) { a =>
    list =>
      val (l, r) = list.splitAt(n)
      l ++ Iterable(a) ++ r.drop(1)
  }

}
