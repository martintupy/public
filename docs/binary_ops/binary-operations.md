class:  default
.left-column[
 # Binary Operations
 #### [Semigroup](#3)
 #### [Monoid](#14)
 #### [Group](#26)
 #### [Band](#)
 #### [Semilattice](#)
 #### [Commutatives](#)
]
- presentation available at [martintupy.github.io/showcase](https://martintupy.github.io/showcase/)
- sources available at [github.com/martintupy/showcase](https://github.com/martintupy/showcase)
- exercises at [github.com/martintupy/tryhard](https://github.com/martintupy/tryhard)
- cats at [typelevel.org/cats/](https://typelevel.org/cats/)
- cats type classes at [typelevel.org/cats/typeclasses.html](https://typelevel.org/cats/typeclasses.html)
- cats semigroup, monoid at [typelevel.org/cats/typeclasses/semigroup.html](https://typelevel.org/cats/typeclasses/semigroup.html)

1. download and install `sbt` [scala-sbt.org](https://www.scala-sbt.org/)
  - `brew install sbt`
2. clone [github.com/martintupy/tryhard](https://github.com/martintupy/tryhard)
  - `git clone https://github.com/martintupy/tryhard`
3. open in IntellIJ (or other IDE)
  - File > Open > tryhard/build.sbt > Open as project > OK
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Binary Operations
### - properties
]

- .accent[Closure] - `x ∊ A, y ∊ A => x • y ∊ A`
- .accent[Associativity] - `((x • y) • z) == (x • (y • z))`
- .accent[Cummutativity] - `x • y == y • x`  
- .accent[Identity element, Neutral unit] - `Ø, x • Ø == Ø • x == x`
- .accent[Inverse] - `x • x' == Ø`
- .accent[Idempotence] - `x • y == x • y • y == x • y • y • y • ...` 
- .accent[Distributivity] - `z ⊗ (x ⊕ y) == (z ⊗ x) ⊕ (z ⊗ y)`

<!-------------------------------------------------------------------------->
---
class: center, middle, default
# Semigroup ❎

<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
]

```scala
def sumInt(x: Int, y: Int): Int = x + y

def sumDouble(x: Double, y: Double): Double = x + y

def sumLong(x: Long, y: Long): Long = x + y

def concatString(x: String, y: String): String = x ++ y

def concatList(x: List[Int], y: List[Int]): List[Int] = x ++ y

def unionIntSet(x: Set[Int], y: Set[Int]): Set[Int] = x union y

def unionSet[A](x: Set[A], y: Set[A]): Set[A] = x union y

def concatList[A](x: List[A], y: List[A]): List[A] = x ++ y

def connect[A](x: A, y: A): A = ??? 
```
<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
]

#### Binary operation
 - .accent[closed] `x ∊ A, y ∊ A => x • y ∊ A` 
 - .accent[associative] `((x • y) • z) == (x • (y • z))`

```scala
trait Semigroup[A] {
  def combine(x: A, y: A): A
}
```
- Closure - .yellow[ensured with parametricity, [A]]
- Associativity <br>
.mono[combine(x, combine(y, z)) = combine(combine(x, y), z)]
<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
]

```scala
def sumInt(x: Int, y: Int): Int = x + y

def sumDouble(x: Double, y: Double): Double = x + y

def sumLong(x: Long, y: Long): Long = x + y

def concatString(x: String, y: String): String = x ++ y

def concatList(x: List[Int], y: List[Int]): List[Int] = x ++ y

def unionIntSet(x: Set[Int], y: Set[Int]): Set[Int] = x union y

def unionSet[A](x: Set[A], y: Set[A]): Set[A] = x union y

def concatList[A](x: List[A], y: List[A]): List[A] = x ++ y

def connect[A](x: A, y: A)(implicit semigroup: Semigroup[A]): A = 
  semigroup.combine(x, y)

```

<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
### - example
]

```scala
implicit val intAdditionSemigroup = new Semigroup[Int] {
  def combine(x: Int, y: Int): Int = x + y
}
implicit val doubleAdditionSemigroup = new Semigroup[Double] {
  def combine(x: Double, y: Double): Double = x + y
}
implicit val longAdditionSemigroup = new Semigroup[Long]{
  def combine(x: Long, y: Long): Long = x + y
}
implicit def listConcatSemigroup[A] = new Semigroup[List[A]] {
  def combine(x: List[A], y: List[A]): List[A] = x ++ y
}
implicit val stringConcatSemigroup = new Semigroup[String] {
  def combine(x: String, y: String): String = x ++ y
}
implicit def setIntersectionSemigroup[A] = new Semigroup[Set[A]] {
  def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
}
```
<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
### - example
]

```scala
def connect[A](x: A, y: A)(implicit semigroup: Semigroup[A]): A = 
  semigroup.combine(x, y)

def sumInt(x: Int, y: Int): Int = connect(x, y)

def sumDouble(x: Double, y: Double): Double = connect(x, y)

def sumLong(x: Long, y: Long): Long = connect(x, y)

def concatString(x: String, y: String): String = connect(x, y)

def concatIntList(x: List[Int], y: List[Int]): List[Int] = connect(x, y)

def unionIntSet(x: Set[Int], y: Set[Int]): Set[Int] = connect(x, y)

def unionSet[A](x: Set[A], y: Set[A]): Set[A] = connect(x, y)

def concatList[A](x: List[A], y: List[A]): List[A] = connect(x, y)
```
<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
### - example
]

```scala
def connect[A](x: A, y: A)(implicit semigroup: Semigroup[A]): A = 
  semigroup.combine(x, y)

connect(1, 1)
// 2
connect(1.0, 1.0)
// 2.0
connect(1L, 1L)
// 2L
connect(List(1, 2, 3), List(4, 5, 6))
// List(1, 2, 3, 4, 5, 6)
connect("abc", "def")
// abcdef
connect(Set(1, 2, 3), Set(2, 3, 4))
// Set(2, 3)
```
<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
### - example
]

```scala
def numericMultiplicationSemigroup[N: Numeric] = new Semigroup[N] {
  def combine(x: N, y: N): N = Numeric[N].times(x, y)
}
def numericAdditionSemigroup[N: Numeric] = new Semigroup[N] {
  def combine(x: N, y: N): N = Numeric[N].plus(x, y)
}
def setUnionSemigroup[A] = new Semigroup[Set[A]] {
  def combine(x: Set[A], y: Set[A]): Set[A] = x union y
}
def setIntersectionSemigroup[A] = new Semigroup[Set[A]] {
  def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
}
def mapMergeSemigroup[K, V](implicit semigroup: Semigroup[V]) = 
    new Semigroup[Map[K, V]] {
      def combine(x: Map[K, V], y: Map[K, V]): Map[K, V] = {
        x.foldLeft(y) { case (xAcc, (yKey, yValue)) =>
          val value = semigroup.optionCombine(yValue, xAcc.get(yKey))
          xAcc.updated(yKey, value)
    }}}
```

<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
### - example
### - cats
]

```scala
import cats.Semigroup

implicit def setIntersectionSemigroup[A] = // create instance of Semigroup 
  Semigroup.instance[Set[A]]((x, y) => x intersect y)

val x = Set(1, 2, 3)
val y = Set(1, 2, 3, 4)

setIntersectionSemigroup.combine(x, y) // Set(1, 2, 3)

implicitly[Semigroup[Set[Int]]].combine(x, y) // Set(1, 2, 3)

Semigroup[Set[Int]].combine(x, y) // Set(1, 2, 3)

import cats.syntax.semigroup._

x combine y // Set(1, 2, 3)

x |+| y // Set(1, 2, 3)
```

<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
### - example
### - cats
### - laws
]

```scala
import cats.implicits._

val ints = List(1, 2, 3, 5, 7)
assert(ints.foldLeft(0)(_ |+| _) == ints.foldRight(0)(_ |+| _))

val strings = List("Hey", " ", "you", " ", "there")
assert(strings.foldLeft("")(_ |+| _) == strings.foldRight("")(_ |+| _))

val lists = List(List(1), List(1, 2, 3), List(), List(10, 0))
assert(
  lists.foldLeft(List.empty[Int])(_ |+| _) == 
  lists.foldRight(List.empty[Int])(_ |+| _)
)

val sets = List(Set(1, 2, 3, 5), Set(2, 5), Set(2, 4, 5, 6), Set(2, 3))
assert(
  sets.foldLeft(Set.empty[Int])(_ |+| _) == 
  sets.foldRight(Set.empty[Int])(_ |+| _)
)
```
<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Semigroup
### - motivation
### - core
### - example
### - cats
### - laws
]

.mid.mono[combine(x, combine(y, z)) == <br>combine(combine(x, y), z)]
<!-------------------------------------------------------------------------->
---

class: center, middle, default

# Monoid 🚫❎ 

<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Monoid
### - motivation
] 
```scala
def sumInts(ints: List[Int]): Int = ints.sum

def sumDoubles(doubles: List[Double]): Double = doubles.sum

def sumLongs(longs: List[Long]): Long = longs.sum

def concatIntLists(lists: List[List[Int]]): List[Int] = 
  lists.foldLeft(List.empty[Int])(_ ++ _)

def concatStrings(strings: List[String]) = strings.foldLeft("")(_ ++ _)

def connectAll[A](as: List[A]): A = ???
```


<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Monoid
### - motivation
] 
```scala
def sumInts(ints: List[Int]): Int = ints.foldLeft(0)(_ + _)

def sumDoubles(doubles: List[Double]): Double = doubles.foldLeft(0.0)(_ + _)

def sumLongs(longs: List[Long]): Long = longs.foldLeft(0L)(_ + _)

def concatIntLists(lists: List[List[Int]]): List[Int] = 
  lists.foldLeft(List.empty[Int])(_ ++ _)

def concatStrings(strings: List[String]) = strings.foldLeft("")(_ ++ _)

def connectAll[A](as: List[A]): A = ???
```

<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Monoid
### - motivation
### - core
]

Binary operation
 - .accent[closed] `x ∊ A, y ∊ A => x • y ∊ A` 
 - .accent[associative] `((x • y) • z) == (x • (y • z))`
 - .accent[identity element] `Ø, x • Ø == x == Ø • x`


"Functionality for folding" 
 
```scala
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}
```
- Closure - .yellow[ensured with parametricity, [A]]
- Associativity <br>
.mono[combine(x, combine(y, z)) == combine(combine(x, y), z)]
- Identity element<br>
.mono[combine(x, empty) == combine(empty, x) == x]

<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Monoid
### - motivation
### - core
]

Binary operation
 - .accent[closed] `x ∊ A, y ∊ A => x • y ∊ A`
 - .accent[associative] `((x • y) • z) == (x • (y • z))`
 - .accent[identity element] `Ø, x • Ø == x == Ø • x`


"Functionality for folding" 
 
```scala
trait Monoid[A] extends Semigroup[A] {
  def empty: A
  
  def combineAll(as: Seq[A]): A =
    as.foldLeft(empty)(combine)

  def isEmpty(a: A): Boolean = a == empty
}
```


<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Monoid
### - motivation
### - core
] 
```scala
def sumInts(ints: List[Int]): Int = ints.foldLeft(0)(_ + _)

def sumDoubles(doubles: List[Double]): Double = doubles.foldLeft(0)(_ + _)

def sumLongs(longs: List[Long]): Long = longs.foldLeft(0)(_ + _)

def concatIntLists(lists: List[List[Int]]): List[Int] = 
  lists.foldLeft(List.empty[Int])(_ ++ _)

def concatStrings(strings: List[String]) = strings.foldLeft("")(_ ++ _)

def concatLists[A](lists: List[List[A]]): List[A] = 
  lists.foldLeft(List.empty[A])(_ ++ _)

def unionSets[A](sets: List[Set[A]]): Set[A] = 
  sets.foldLeft(Set.empty[A])(_ union _)

def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = 
  as.foldLeft(monoid.empty)(monoid.combine)
```
<!-------------------------------------------------------------------------->
---

class: default

.left-column[
# Monoid
### - motivation
### - core
] 
```scala
def sumInts(ints: List[Int]): Int = ints.foldLeft(0)(_ + _)

def sumDoubles(doubles: List[Double]): Double = doubles.foldLeft(0)(_ + _)

def sumLongs(longs: List[Long]): Long = longs.foldLeft(0)(_ + _)

def concatIntLists(lists: List[List[Int]]): List[Int] = 
  lists.foldLeft(List.empty[Int])(_ ++ _)

def concatStrings(strings: List[String]) = strings.foldLeft("")(_ ++ _)

def concatLists[A](lists: List[List[A]]): List[A] = 
  lists.foldLeft(List.empty[A])(_ ++ _)

def unionSets[A](sets: List[Set[A]]): Set[A] = 
  sets.foldLeft(Set.empty[A])(_ union _)

def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = 
  monoid.combineAll(as) 
```
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Monoid
### - motivation
### - core
### - example
]

```scala
implicit def numericAdditionMonoid[A: Numeric] = new Monoid[A] {
  def empty: A = Numeric[A].zero
  def combine(x: A, y: A): A = Numeric[A].plus(x, y)
}

implicit def listConcatMonoid[A] = new Monoid[List[A]] {
  def empty: List[A] = List.empty
  def combine(x: List[A], y: List[A]): List[A] = x ++ y
}

implicit def stringConcatMonoid = new Monoid[String] {
  def empty: String = ""
  def combine(x: String, y: String): String = x ++ y
}

implicit def setUnionMonoid[A] = new Monoid[Set[A]] {
  def empty: Set[A] = Set.empty
  def combine(x: Set[A], y: Set[A]): Set[A] = x union y
}
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Monoid
### - motivation
### - core
### - example
]

```scala
def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = 
  as.foldLeft(monoid.empty)(monoid.combine)

def sumInts(ints: List[Int]): Int = connectAll(ints)

def sumDoubles(doubles: List[Double]): Double = connectAll(doubles)

def sumLongs(longs: List[Long]): Long = connectAll(longs)

def concatIntLists(lists: List[List[Int]]): List[Int] = connectAll(lists)

def concatStrings(strings: List[String]): String = connectAll(strings)

def concatLists[A](lists: List[List[A]]): List[A] = connectAll(lists)

def unionSets[A](sets: List[Set[A]]): Set[A] = connectAll(sets)
```
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Monoid
### - motivation
### - core
### - example
]

```scala
def connectAll[A](as: List[A])(implicit monoid: Monoid[A]): A = 
  monoid.combineAll(as)

connectAll(List(1, 2, 0, 10, 11))
// 24
connectAll(List(2.5, 10.0, 7.25))
// 19.75
connectAll(List(List(1, 2, 3), List(4, 10, 11), List(0, 1, 2)))
// List(1, 2, 3, 4, 10, 11, 0, 1, 2)
connectAll(List(Set(1, 2, 3), Set.empty[Int], Set(2, 3, 10), Set(1)))
// Set(1, 2, 3, 10)
connectAll(List("Hey", " ", "you ", "there", "!"))
// Hey you there!
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Monoid
### - motivation
### - core
### - example
### - cats
]

```scala
import cats.{Monoid, Semigroup}
import cats.syntax.monoid._
implicit def optionMonoid[A: Semigroup]: Monoid[Option[A]] = Monoid.instance(
  None,
  (xOpt, yOpt) => xOpt.fold(yOpt)(x => yOpt.fold(xOpt)(y => Some(x |+| y)))
)
val numberOptions = List(Some(1), None, Some(2), Some(23), Some(0), None)
import cats.instances.int._

numberOptions.foldLeft(optionMonoid.empty)(optionMonoid.combine)
// Some(26)
numberOptions.foldLeft(Monoid[Option[Int]].empty)(Monoid[Option[Int]].combine)
// Some(26)
numberOptions.foldLeft(Monoid[Option[Int]].empty)(_ combine _)
// Some(26)
numberOptions.foldLeft(Monoid[Option[Int]].empty)(_ |+| _)
// Some(26)
Monoid[Option[Int]].combineAll(numberOptions)
// Some(26)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Monoid
### - motivation
### - core
### - example
### - cats
### - laws
]

.mid.mono[
   combine(x, combine(y, z)) == <br> 
   combine(combine(x, y), z)
   
   <br><br>
   
   combine(x, empty) == <br>
   combine(empty, x) == <br>
   x
]
<!-------------------------------------------------------------------------->
---

class: center, middle, default

# Group 🚫❎🔄 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - core
]

Binary operation
 - .accent[closed] `x ∊ A, y ∊ A => x • y ∊ A` 
 - .accent[associative] `((x • y) • z) == (x • (y • z))`
 - .accent[identity element] `Ø, x • Ø == x == Ø • x`
 - .accent[inverse] `x • x' == Ø`


```scala
trait Group[A] extends Monoid[A] {
  def inverse(a: A): A
}
```
- Closure - .yellow[ensured with parametricity, [A]]
- Associativity <br>
.mono[combine(x, combine(y, z)) == combine(combine(x, y), z)]
- Identity element<br>
.mono[combine(x, empty) == combine(empty, x) == x]
- Inverse <br>
.mono[combine(x, inverse(x)) == empty]
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - core
]

Binary operation
 - .accent[closed] `x ∊ A, y ∊ A => x • y ∊ A` 
 - .accent[associative] `((x • y) • z) == (x • (y • z))`
 - .accent[identity element] `Ø, x • Ø == x == Ø • x`
 - .accent[inverse] `x • x' == Ø`


```scala
trait Group[A] extends Monoid[A] {
  def inverse(a: A): A

  def remove(x: A, y: A): A = combine(x, inverse(y))
}
```
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - core
### - examples
]
```scala
implicit val intGroup = new Group[Int] {
  def inverse(a: Int): Int = -a
  def empty: Int = 0
  def combine(x: Int, y: Int): Int = x + y
}

implicit val longGroup = new Group[Long] {
  def inverse(a: Long): Long = -a
  def empty: Long = 0L
  def combine(x: Long, y: Long): Long = x + y
}

implicit val doubleGroup = new Group[Double] {
  def inverse(a: Double): Double = -a
  def empty: Double = 0.0
  def combine(x: Double, y: Double): Double = x + y
}
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - core
### - examples
]
```scala
implicit def numericGroup[A: Numeric] = new Group[A] {
  def inverse(a: A): A = Numeric[A].negate(a)
  def empty: A = Numeric[A].zero
  def combine(x: A, y: A): A = Numeric[A].plus(x, y)
}

implicit val finiteDurationGroup = new Group[FiniteDuration] {
  def inverse(a: FiniteDuration): FiniteDuration = -a
  def empty: FiniteDuration = Duration.Zero
  def combine(x: FiniteDuration, y: FiniteDuration): FiniteDuration = x - y
}
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - core
### - examples
]

[en.wikipedia.org/wiki/Examples_of_groups#The_set_of_maps](https://en.wikipedia.org/wiki/Examples_of_groups#The_set_of_maps) <br><br><br>


let <br>
.accent[
- S: Set <br>
- G: Group <br>
- M: Map[S, G] <br>
]   

then <br>
.accent[
- M: Group <br>
]

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - core
### - examples
]
```scala
implicit def mapGroup[K, V: Group]: Group[Map[K, V]] = new Group[Map[K, V]] {
  def inverse(a: Map[K, V]): Map[K, V] =
    a.view.mapValues(Group[V].inverse).toMap
      .filterNot { case (_, v) => Group[V].isEmpty(v) }

  def empty: Map[K, V] = Map.empty

  def combine(x: Map[K, V], y: Map[K, V]): Map[K, V] =
    Semigroup[Map[K, V]].combine(x, y)
      .filterNot { case (_, v) => Group[V].isEmpty(v) }
}
```