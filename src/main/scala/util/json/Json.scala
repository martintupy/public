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

package util.json

import monocle.{Optional, Prism}

sealed trait Json

case object JNull extends Json

case class JStr(v: String) extends Json

case class JNum(v: Double) extends Json

case class JBool(v: Boolean) extends Json

case class JArr(v: Vector[Json]) extends Json

case class JObj(v: Map[String, Json]) extends Json

object Json {
  val double: Prism[Json, Double] = jsonJNum composeIso jNumDouble
  val string: Prism[Json, String] = jsonJStr composeIso jStrString
  val vector: Prism[Json, Vector[Json]] = jsonJArr composeIso jArrVector
  val map: Prism[Json, Map[String, Json]] = jsonJObj composeIso jObjMap
  val bool: Prism[Json, Boolean] = jsonJBool composeIso jBoolBool
  def objJson(k: String): Optional[Json, Json] = jsonJObj composeOptional jObjJson(k)
  def arrJson(n: Int): Optional[Json, Json] = jsonJArr composeOptional jArrJson(n)
}
