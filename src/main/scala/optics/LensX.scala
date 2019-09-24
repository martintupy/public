package optics

import monocle.macros.GenLens
import monocle.macros.syntax.lens._


object LensX extends App {

  case class Street(number: Int, name: String)
  case class Address(city: String, street: Street)
  case class Company(name: String, address: Address)
  case class Employee(name: String, company: Company)

  trait Lens[S, A] { self =>

    // s->a
    def get(s: S): A

    // a->s->s
    def set(a: A)(s: S): S

    // (a->a)->s->s
    def modify(f: A => A)(s: S): S = set(f(get(s)))(s)


    def compose[B](other: Lens[A, B]): Lens[S, B] = new Lens[S, B] { // types match S -> A -> B

      // s->b
      def get(s: S): B = other.get(self.get(s))

      // b->s->s
      def set(b: B)(s: S): S = self.set(other.set(b)(self.get(s)))(s)
    }
  }

  val address = Address("london", Street(23, "High"))

  val streetName = new Lens[Street, String] {
    def get(s: Street): String = s.name

    def set(a: String)(s: Street): Street = s.copy(name = a)
  }

  val addressStreet = new Lens[Address, Street] {
    def get(s: Address): Street = s.street

    def set(a: Street)(s: Address): Address = s.copy(street = a)
  }

  val usingLens = addressStreet.compose(streetName).modify(_ + " Street")(address)
  val usingCopy = address.copy(street = address.street.copy(name = address.street.name + "Street"))

  def getSet[S, A](l: Lens[S, A], s: S): Boolean = l.set(l.get(s))(s) == s
  def setGet[S, A](l: Lens[S, A], s: S, a: A): Boolean = l.get(l.set(a)(s)) == a

  GenLens[Address](_.street.name)


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

  employee
    .lens(_.name).set("Mike")
    .lens(_.company.address.city).modify(_.toUpperCase)
    .lens(_.company.address.street.name).set("long street")
}
