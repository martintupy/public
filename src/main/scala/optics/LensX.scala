package optics

import monocle.macros.syntax.lens._


object LensX extends App {

  case class Street(number: Int, name: String)
  case class Address(city: String, street: Street)
  case class Company(name: String, address: Address)
  case class Employee(name: String, company: Company)


  val employee = Employee("john", Company("awesome inc", Address("london", Street(23, "high street"))))

  // Using Lens
  employee.lens(_.company.address.street.number).modify(_ + 100)

  // Using scala copy
  employee.copy(
    company = employee.company.copy(
      address = employee.company.address.copy(
        street = employee.company.address.street.copy(
          number = employee.company.address.street.number + 100
        )
      )
    ))
}
