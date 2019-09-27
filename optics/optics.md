class:  default
.left-column[
# First things first 
]
.right-column[
- available links - justpaste.it/1tu03
- presentation available at [raw.githubusercontent.com/martintupy/showcase/master/optics/optics.html](https://raw.githubusercontent.com/martintupy/showcase/master/optics/optics.html)
1. download and install `sbt` [scala-sbt.org](https://www.scala-sbt.org/)
  - `brew install sbt`
2. clone [github.com/martintupy/tryhard](https://github.com/martintupy/tryhard)
  - `git clone https://github.com/martintupy/tryhard`
3. open in IntellIJ (or other IDE)
  - File > Open > tryhard/build.sbt > Open as project > OK
]
---
class: center, middle, default
# Lens

---

class: default

.left-column[
# Lens
### - core
]

.right-column[
A Lens is an optic (purely functional abstraction over `get` and `set`)
mostly for Product types, e.g. `case class`, `Tuple` , `HList` ...

`Lens[S, A]` where: 
 - `S` represents the Product
 - `A` an element inside of `S`

```scala
trait Lens[S, A] {
  def get(s: S): A

  def set(a: A)(s: S): S
}
```
- get: `S => A`
- set: `A => S => S`
]

---
class: default

.left-column[
# Lens
### - core
]

.right-column[
A Lens is an optic (purely functional abstraction over `get` and `set`)
mostly for Product types, e.g. `case class`, `Tuple` , `HList` ...

`Lens[S, A]` where: 
 - `S` represents the Product
 - `A` an element inside of `S`

```scala
trait Lens[S, A] {
  def get(s: S): A

  def set(a: A)(s: S): S

  def modify(f: A => A)(s: S): S = set(f(get(s)))(s)
}
```
- get: `S => A`
- set: `A => S => S`
- modify: `(A => A) => S => S`
]

---
class: default
.left-column[
# Lens
### - core
### - example
]

.right-column[
```scala
case class Street(number: Int, name: String)

val streetName = new Lens[Street, String] {  
  def get(s: Street): String = s.name  
  
  def set(a: String)(s: Street): Street = s.copy(name = a)  
}

val street = Street(23, "High")
// Street(23, High)

streetName.modify(_ + " Street")(street)
// Street(23, Hight Street)
```
]
---
class: default
.left-column[
#Lens
### - core
### - example
]

.right-column[
```scala
case class Street(number: Int, name: String)
case class Address(city: String, street: Street)  
  
val streetName = new Lens[Street, String] {  
  def get(s: Street): String = s.name  
  def set(a: String)(s: Street): Street = s.copy(name = a)  
}  
  
val addressStreet = new Lens[Address, Street] {  
  def get(s: Address): Street = s.street
  def set(a: Street)(s: Address): Address = s.copy(street = a)  
}

val street = Street(23, "High")
val address = Address("London", street)

// (address ??? street).modify(_ + " Street") 
```
]

---
class: default
.left-column[
#Lens
### - core
### - example
### - composition
]

.right-column[
```scala
trait Lens[S, A] { self =>  
  def get(s: S): A  
  
  def set(a: A)(s: S): S  
  
  def modify(f: A => A)(s: S): S = set(f(get(s)))(s)  
  
  def compose[B](other: Lens[A, B]): Lens[S, B] = new Lens[S, B] {
  
    def get(s: S): B = other.get(self.get(s))  
  
    def set(b: B)(s: S): S = self.set(other.set(b)(self.get(s)))(s)  
  }  
}
```
- types in compose need to match `S => A`, `A => B`  ->  `S => B`
]
---
class: default
.left-column[
#Lens
### - core
### - example
### - composition
### - composition example
]

.right-column[
```scala
case class Street(number: Int, name: String)
case class Address(city: String, street: Street)
val streetName = new Lens[Street, String] {  
  def get(s: Street): String = s.name  
  def set(a: String)(s: Street): Street = s.copy(name = a)  
}  
val addressStreet = new Lens[Address, Street] {  
  def get(s: Address): Street = s.street
  def set(a: Street)(s: Address): Address = s.copy(street = a)  
}
val street = Street(23, "High")
val address = Address("London", street)   
val usingLens = addressStreet.compose(streetName).modify(_ + " Street")
val usingCopy = address.copy(street = address.street
  .copy(name = address.street.name + "Street")
)
usingLens(address)
// Address(London, Street(23, Hight Street))
```
]
---
class: default
.left-column[
#Lens
### - core
### - example
### - composition
### - composition example
### - laws
]

.right-column[
```scala
def getSet[S, A](l: Lens[S, A], s: S): Boolean = l.set(l.get(s))(s) == s
def setGet[S, A](l: Lens[S, A], s: S, a: A): Boolean = l.get(l.set(a)(s)) == a
```
]

---

class: center, middle, default
# Monocle

---
class: default
.left-column[
# Monocle
]
.right-column[
- [julien-truffaut.github.io/Monocle/optics/lens.html](http://julien-truffaut.github.io/Monocle/optics/lens.html)

- sbt

`libraryDependencies += "com.github.julien-truffaut" %% "monocle" % "2.0.0"`

- gradle

`group: "com.github.julien-truffaut" name: "monocle" version: "2.0.0"`

- compatible with cats 2.0
]
---
class: default
.left-column[
# Monocle
### - generic lens
]
.right-column[
- using scala macros to access fields

```scala
import monocle.macros.GenLens

case class Street(number: Int, name: String)
case class Address(city: String, street: Street)

GenLens[Street](_.name)
```
- GenLens has _built in_ compose

```scala
val street = Street(23, "High")
val address = Address("London", street) 

GenLens[Address](_.street.name).modify(_ + " Street")(address)
// Address(London, Street(23, Hight Street))

GenLens[Address](_.street.name).set("Long Street")(address)
// Address(London, Street(23, Long Street))
```
]
---
class: default
.left-column[
# Monocle
### - generic lens
### - convenient syntax
]
.right-column[
```scala
import monocle.macros.syntax.lens._

case class Street(number: Int, name: String)  
case class Address(city: String, street: Street)  
case class Company(name: String, address: Address)  
case class Employee(name: String, company: Company)
val employee = Employee("john", Company("awesome inc", 
  Address("london", Street(23, "high street"))
))
// Using Lens composition 
employee.lens(_.company.address.street.number).modify(_ + 100)  

// Using scala copy  
employee.copy(  
  company = employee.company.copy(  
    address = employee.company.address.copy(  
      street = employee.company.address.street.copy(  
        number = employee.company.address.street.number + 100  
))))
```
]
---
class: default
.left-column[
# Monocle
### - generic lens
### - convenient syntax
]
.right-column[
can be also chained, to modify on multiple levels
```scala
employee  
  .lens(_.name).set("Mike")  
  .lens(_.company.address.city).modify(_.toUpperCase)  
  .lens(_.company.address.street.name).set("long street")
// Employee(Mike, Company(awesome inc, Address(LONDON, Street(23, long street))))
```
]
---

class: center, middle, default

# Optics

---
class: default
.left-column[
  # Optics

]
.right-column[
Optics are a group of purely functional abstractions to manipulate `get`, `set` of immutable objects.
- `Iso` - Isomorphic types
- `Prism` - Coproduct (Sum) types 
- `Lens` - Product Types
- `Optional` - Generalized for (Lens + Prism)

![](./optics-diagram.png)
]
---

class: center, middle, default
# Iso
---
class: default
.left-column[
# Iso
### - core
]

.right-column[
- isomorphic types (in sense what data they represent)
- `Iso[S, A]`
- `S` contains same data as `A`

```scala
trait Iso[S, A] {
  def get(a: S): A

  def reverseGet(a: A): S
}
```

  - get: `A => B`
  - reverseGet: `B => A`
]
---
class: default
.left-column[
# Iso
### - core
]

.right-column[
- isomorphic types (in sense what data they represent)
- `Iso[S, A]`
- `S` contains same data as `A`

```scala
trait Iso[S, A] {
  def get(a: S): A

  def reverseGet(a: A): S

  def reverse: Iso[A, S] = new Iso[A, S] {
    def get(a: A): S = self.reverseGet(a)

    def reverseGet(a: S): A = self.get(a)
  }
}
```
  - get: `A => S`
  - reverseGet: `S => A`
  - reverse: swapped `A` for `S`
]
---
class: default
.left-column[
# Iso
### - core
### - iso examples
]
.right-column[

- `List` <=> `Vector`
- `Map[K, V]` <=> `List[(K, V)]`
- `String` <=> `List[Char]`
- `case class` <=> `Tuple` <=> `HList` *(size <= 22)
- wrapped one field in case class <=> field
  - `case class JsonNum(n: Double)` <=> `Double`
- Celsius <=> Fahrenheit
- meter <=> centimeter
- Gigabyte <=> KiloByte
- ...

]
---
class: default
.left-column[
# Iso
### - core
### - iso examples
]

.right-column[
```scala
//Server app
case class ServerStreet(number: Int, name: String)  
case class ServerAddress(city: String, street: ServerStreet)  
case class ServerCompany(name: String, address: ServerAddress)  
case class ServerEmployee(name: String, company: ServerCompany)  

//Client app
case class ClientStreet(number: Int, name: String)  
case class ClientAddress(city: String, street: ClientStreet)  
case class ClientCompany(name: String, address: ClientAddress)  
case class ClientEmployee(name: String, company: ClientCompany)  
```
]
---
class: default
.left-column[
# Iso
### - core
### - iso examples
]

.right-column[
```scala
val serverEmployee = ServerEmployee("john", ServerCompany("awesome inc", 
  ServerAddress("london", ServerStreet(23, "high street"))
))  
val serverToClientIso = Iso[ServerEmployee, ClientEmployee] { employee =>  
  ClientEmployee(employee.name,  
    ClientCompany(employee.company.name,  
      ClientAddress(employee.company.address.city,  
        ClientStreet(employee.company.address.street.number, 
          employee.company.address.street.name
  ))))
} { employee =>  
  ServerEmployee(employee.name,  
    ServerCompany(employee.company.name,  
      ServerAddress(employee.company.address.city,  
        ServerStreet(employee.company.address.street.number,
          employee.company.address.street.name
))))}  
val client: ClientEmployee = serverToClientIso.get(serverEmployee)  
```
]
---
class: default
.left-column[
# Iso
### - core
### - iso examples
### - laws
]

.right-column[

```scala
def roundTripOneWay[S, A](i: Iso[S, A], s: S): Boolean = i.reverseGet(i.get(s)) == s  
def roundTripOtherWay[S, A](i: Iso[S, A], a: A): Boolean = i.get(i.reverseGet(a)) == a
```
]
---
class: default, center
# To be continued 

.left[
### 1. Prism, Optional
### 2. All combined, more useful functions using optics
]
---
class: default, center, middle
# Tryhard time
---
class: default
.left-column[
  # Tryhard time
]
.right-column[
  - optics.Iso
    - `def composeIso[B](other: Iso[A, B]): Iso[S, B]` (1) 
    - `def asLens: Lens[S, A] = new Lens[S, A]` (1)
    - `def composeLens[B](lens: Lens[A, B]): Lens[S, B]` (2)

- optics.Lens
  - `def composeIso[B](iso: Iso[A, B]): Lens[S, B]` (2)

- optics.WareHouse
  - `val normalizedStorage: WareHouse` (3)
- Lens, Iso - build
- WareHouse - run main

1. implement 1./2.
2. implement 3./4., Use already implemented methods
3. `import monocle.macros.syntax.lens._`, chained lens syntax
]