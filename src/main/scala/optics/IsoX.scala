package optics

import monocle.{Iso, PIso}
import monocle.syntax.all._

object IsoX extends App {

  // Server
  case class ServerStreet(number: Int, name: String)
  case class ServerAddress(city: String, street: ServerStreet)
  case class ServerCompany(name: String, address: ServerAddress)
  case class ServerEmployee(name: String, company: ServerCompany)

  // Client
  case class ClientStreet(number: Int, name: String)
  case class ClientAddress(city: String, street: ClientStreet)
  case class ClientCompany(name: String, address: ClientAddress)
  case class ClientEmployee(name: String, company: ClientCompany)

  val clientEmployee = ClientEmployee("john", ClientCompany("awesome inc", ClientAddress("london", ClientStreet(23, "high street"))))
  val serverEmployee = ServerEmployee("john", ServerCompany("awesome inc", ServerAddress("london", ServerStreet(23, "high street"))))

  val serverToClient = Iso[ServerEmployee, ClientEmployee] { employee =>
    ClientEmployee(employee.name,
      ClientCompany(employee.company.name,
        ClientAddress(employee.company.address.city,
          ClientStreet(employee.company.address.street.number, employee.company.address.street.name)
        )
      )
    )
  } { employee =>
    ServerEmployee(employee.name,
      ServerCompany(employee.company.name,
        ServerAddress(employee.company.address.city,
          ServerStreet(employee.company.address.street.number, employee.company.address.street.name)
        )
      )
    )
  }

  val clientToServer: Iso[ClientEmployee, ServerEmployee] = serverToClient.reverse

  val client: ClientEmployee = serverToClient.get(serverEmployee)
  val server: ServerEmployee = clientToServer.get(clientEmployee)
  val serverReverse: ServerEmployee = serverToClient.reverseGet(clientEmployee)
}
