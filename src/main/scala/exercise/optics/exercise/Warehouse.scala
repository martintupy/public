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

import java.text.Normalizer

import monocle.macros.syntax.lens._

object Warehouse extends App {


  case class Company(name: String, residence: Address)

  case class Street(name: String, number: Int)

  case class Address(city: String, postalCode: Int, street: Street)

  case class WareHouse(name: String, address: Address, company: Company)

  val storage = WareHouse(
    "Sklad Praha 7 - Holešovice", Address("Prague 7", 17000, Street("Bubenské nábřeží", 13)),
    Company("Perfect storage s.r.o.", Address("Prague 1", 11000, Street("Ovocný trh", 12)))
  )

  def normalize(s: String): String = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[\u0300-\u036f]", "")


  def normalizeAddress(address: Address) = address
    .lens(_.city).modify(normalize)
    .lens(_.street.name).modify(normalize)

  val normalizedStorage: WareHouse = storage
    .lens(_.name).modify(normalize)
    .lens(_.address).modify(normalizeAddress)
    .lens(_.company.residence).modify(normalizeAddress)
    .lens(_.company.name).modify(normalize)

  val result = WareHouse(
    "Sklad Praha 7 - Holesovice", Address("Prague 7", 17000, Street("Bubenske nabrezi", 13)),
    Company("Perfect storage s.r.o.", Address("Prague 1", 11000, Street("Ovocny trh", 12)))
  )

  assert(result == normalizedStorage)

}
