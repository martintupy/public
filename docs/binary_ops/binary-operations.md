
class: center, middle, default
# Binary Operations
#### Programming (Scala)

<!-------------------------------------------------------------------------->
---

class:  default
.left-column[
 # Binary Operations
 #### [Semigroup](#5)
 #### [Monoid](#39)
 #### [Group](#71)
 #### [Band](#)
 #### [Semilattice](#)
 #### [Commutatives](#)
]
- presentation available at [martintupy.github.io/showcase](https://martintupy.github.io/showcase/)
- sources available at [github.com/martintupy/showcase](https://github.com/martintupy/showcase)
- cats at [typelevel.org/cats/](https://typelevel.org/cats/)
- cats type classes at [typelevel.org/cats/typeclasses.html](https://typelevel.org/cats/typeclasses.html)
- cats semigroup, monoid at [typelevel.org/cats/typeclasses/semigroup.html](https://typelevel.org/cats/typeclasses/semigroup.html)
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Binary Operations
### - properties
]
<br>
<br>
.accent[Closure] - `x ∊ A, y ∊ A => x • y ∊ A` <br>
.accent[Associativity] - `((x • y) • z) == (x • (y • z))` <br>
.accent[Cummutativity] - `x • y == y • x` <br>
.accent[Identity element, Neutral unit] - `Ø, x • Ø == Ø • x == x` <br>
.accent[Inverse] - `x • x' == Ø` <br>
.accent[Idempotence] - `x • y == x • y • y == x • y • y • y • ...` <br> 
.accent[Distributivity] - `z ⊗ (x ⊕ y) == (z ⊗ x) ⊕ (z ⊗ y)` <br>
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Binary Operations
### - properties
]
<br>
<br>
.accent[Closure] - `x ∊ A, y ∊ A => x • y ∊ A` <br>
.accent[Associativity] - `((x • y) • z) == (x • (y • z))` <br>
<br>
.accent[Identity element, Neutral unit] - `Ø, x • Ø == Ø • x == x` <br>
.accent[Inverse] - `x • x' == Ø` <br>

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
```

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

```

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

```
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
```

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

#### Binary operation
 - .accent[closed] `x ∊ A, y ∊ A => x • y ∊ A` 
 - .accent[associative] `((x • y) • z) == (x • (y • z))`

```scala
trait Semigroup[A] {
  def combine(x: A, y: A): A
}

object Semigroup {
  def apply[A](implicit semigroup: Semigroup[A]) = semigroup
  
  def maybeCombine[A](xOpt: Option[A], y: A)
    (implicit semigroup: Semigroup[A]): A =
      xOpt.fold(y)(x => semigroup.combine(x, y))

  def maybeCombine[A](x: A, yOpt: Option[A])
    (implicit semigroup: Semigroup[A]): A =
      yOpt.fold(x)(y => semigroup.combine(x, y))
}
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

object Semigroup {
  def apply[A](implicit semigroup: Semigroup[A]) = semigroup
  
  def maybeCombine[A: Semigroup](xOpt: Option[A], y: A): A =
    xOpt.fold(y)(x => Semigroup[A].combine(x, y))

  def maybeCombine[A: Semigroup](x: A, yOpt: Option[A]): A =
    yOpt.fold(x)(y => Semigroup[A].combine(x, y))
}
```
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
implicit val stringConcatSemigroup = new Semigroup[String] {
  def combine(x: String, y: String): String = x ++ y
}
implicit def listConcatSemigroup[A] = new Semigroup[List[A]] {
  def combine(x: List[A], y: List[A]): List[A] = x ++ y
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
implicit val intAdditionSemigroup = new Semigroup[Int] {
  def combine(x: Int, y: Int): Int = x + y
}
implicit val doubleAdditionSemigroup = new Semigroup[Double] {
  def combine(x: Double, y: Double): Double = x + y
}
implicit val longAdditionSemigroup = new Semigroup[Long]{
  def combine(x: Long, y: Long): Long = x + y
}
implicit val stringConcatSemigroup = new Semigroup[String] {
  def combine(x: String, y: String): String = x ++ y
}
implicit def listConcatSemigroup[A] = new Semigroup[List[A]] {
  def combine(x: List[A], y: List[A]): List[A] = x ++ y
}
implicit def setUnionSemigroup[A] = new Semigroup[Set[A]] {
  def combine(x: Set[A], y: Set[A]): Set[A] = x union y
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
def sumInt(x: Int, y: Int): Int = connect(x, y)

def sumDouble(x: Double, y: Double): Double = connect(x, y)

def sumLong(x: Long, y: Long): Long = connect(x, y)

def concatString(x: String, y: String): String = connect(x, y)

def concatList(x: List[Int], y: List[Int]): List[Int] = connect(x, y)

def unionIntSet(x: Set[Int], y: Set[Int]): Set[Int] = connect(x, y)

def unionSet[A](x: Set[A], y: Set[A]): Set[A] = connect(x, y)

def concatList[A](x: List[A], y: List[A]): List[A] = connect(x, y)

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
// Set(1, 2, 3, 4)
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

def setIntersectionSemigroup[A] = new Semigroup[Set[A]] {
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
def numericMultiplicationSemigroup[N: Numeric] = new Semigroup[N] {
  def combine(x: N, y: N): N = Numeric[N].times(x, y)
}

def numericAdditionSemigroup[N: Numeric] = new Semigroup[N] {
  def combine(x: N, y: N): N = Numeric[N].plus(x, y)
}

def setIntersectionSemigroup[A] = new Semigroup[Set[A]] {
  def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
}

def mapMergeSemigroup[K, V](implicit semigroup: Semigroup[V]) = 
    new Semigroup[Map[K, V]] {
      def combine(x: Map[K, V], y: Map[K, V]): Map[K, V] = {
        x.foldLeft(y) { case (xAcc, (yKey, yValue)) =>
          val value = Semigroup.maybeCombine(yValue, xAcc.get(yKey))
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
import cats.syntax.semigrou._ // infix syntax for Semigroup instances

import cats.instances.int._ // implicit intAdditionSemigroup
val ints = List(1, 2, 3, 5, 7)
assert(ints.foldLeft(0)(_ |+| _) == ints.foldRight(0)(_ |+| _))

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
import cats.syntax.semigrou._ // infix syntax for Semigroup instances

import cats.instances.int._ // implicit intAdditionSemigroup
val ints = List(1, 2, 3, 5, 7)
assert(ints.foldLeft(0)(_ |+| _) == ints.foldRight(0)(_ |+| _))

import cats.instances.list._ // implicit listConcatenationSemigroup
val lists = List(List(1), List(1, 2, 3), List(), List(10, 0))
assert(
  lists.foldLeft(List.empty[Int])(_ |+| _) == 
  lists.foldRight(List.empty[Int])(_ |+| _)
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

```scala
import cats.syntax.semigrou._ // infix syntax for Semigroup instances

import cats.instances.int._ // implicit intAdditionSemigroup
val ints = List(1, 2, 3, 5, 7)
assert(ints.foldLeft(0)(_ |+| _) == ints.foldRight(0)(_ |+| _))

import cats.instances.list._ // implicit listConcatenationSemigroup
val lists = List(List(1), List(1, 2, 3), List(), List(10, 0))
assert(
  lists.foldLeft(List.empty[Int])(_ |+| _) == 
  lists.foldRight(List.empty[Int])(_ |+| _)
)

import cats.instances.set._ // implicit setUnionSemigroup
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

```

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

def concatStrings(strings: List[String]): String =
  strings.foldLeft("")(_ ++ _)
```


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

def concatStrings(strings: List[String]): String =
  strings.foldLeft("")(_ ++ _)

def concatLists[A](lists: List[List[A]]): List[A] = 
  lists.foldLeft(List.empty[A])(_ ++ _)

```

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

def concatStrings(strings: List[String]): String = 
  strings.foldLeft("")(_ ++ _)

def concatLists[A](lists: List[List[A]]): List[A] = 
  lists.foldLeft(List.empty[A])(_ ++ _)

def unionSets[A](sets: List[Set[A]]): Set[A] = 
  sets.foldLeft(Set.empty[A])(_ union _)  

```

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

def concatStrings(strings: List[String]): String = 
  strings.foldLeft("")(_ ++ _)

def concatLists[A](lists: List[List[A]]): List[A] = 
  lists.foldLeft(List.empty[A])(_ ++ _)

def unionSets[A](sets: List[Set[A]]): Set[A] = 
  sets.foldLeft(Set.empty[A])(_ union _)

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

def concatStrings(strings: List[String]): String = 
  strings.foldLeft("")(_ ++ _)

def concatLists[A](lists: List[List[A]]): List[A] = 
  lists.foldLeft(List.empty[A])(_ ++ _)

def unionSets[A](sets: List[Set[A]]): Set[A] = 
  sets.foldLeft(Set.empty[A])(_ union _)

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

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
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

def connectAll[A](as: List[A): A = ???
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

implicit def stringConcatMonoid = new Monoid[String] {
  def empty: String = ""
  def combine(x: String, y: String): String = x ++ y
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
implicit def numericAdditionMonoid[A: Numeric] = new Monoid[A] {
  def empty: A = Numeric[A].zero
  def combine(x: A, y: A): A = Numeric[A].plus(x, y)
}

implicit def stringConcatMonoid = new Monoid[String] {
  def empty: String = ""
  def combine(x: String, y: String): String = x ++ y
}

implicit def listConcatMonoid[A] = new Monoid[List[A]] {
  def empty: List[A] = List.empty
  def combine(x: List[A], y: List[A]): List[A] = x ++ y
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
implicit def numericAdditionMonoid[A: Numeric] = new Monoid[A] {
  def empty: A = Numeric[A].zero
  def combine(x: A, y: A): A = Numeric[A].plus(x, y)
}

implicit def stringConcatMonoid = new Monoid[String] {
  def empty: String = ""
  def combine(x: String, y: String): String = x ++ y
}

implicit def listConcatMonoid[A] = new Monoid[List[A]] {
  def empty: List[A] = List.empty
  def combine(x: List[A], y: List[A]): List[A] = x ++ y
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
def sumInts(ints: List[Int]): Int = connectAll(ints)

def sumDoubles(doubles: List[Double]): Double = connectAll(doubles)

def sumLongs(longs: List[Long]): Long = connectAll(longs)

def concatIntLists(lists: List[List[Int]]): List[Int] = 
  connectAll(lists)

def concatStrings(strings: List[String]): String = connectAll(strings)

def concatLists[A](lists: List[List[A]]): List[A] = 
  connectAll(lists)

def unionSets[A](sets: List[Set[A]]): Set[A] = 
  connectAll(sets)

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

<!-----------------------------------#34--------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

<!-----------------------------------#34--------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-01]

  <table>
    <tr>
      <th>Domain</th>
      <th>visitors</th>
    </tr>
    <tr class="yellow">
      <td>domain1.com</td>
      <td>1</td>
    </tr>
  </table> 
  
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-02]

  <table>
    <tr>
      <th>Domain</th>
      <th>visitors</th>
    </tr>
    <tr class="yellow">
      <td>domain1.com</td>
      <td>13</td>
    </tr>
  </table> 
  
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-03]
  <table>
     <tr>
       <th>Domain</th>
       <th>visitors</th>
     </tr>
     <tr class="yellow">
       <td>domain1.com</td>
       <td>6</td>
     </tr>
     <tr class="yellow">
       <td>domain2.com</td>
       <td>3</td>
     </tr>
     <tr class="yellow">
       <td>domain3.com</td>
       <td>3</td>
     </tr>
   </table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-04]
  <table>
       <tr>
         <th>Domain</th>
         <th>visitors</th>
       </tr>
       <tr class="yellow">
         <td>domain1.com</td>
         <td>1</td>
       </tr>
       <tr class="yellow">
         <td>domain2.com</td>
         <td>3</td>
       </tr>
       <tr class="yellow">
         <td>domain3.com</td>
         <td>2</td>
       </tr>
       <tr class="yellow">
         <td>domain4.com</td>
         <td>1</td>
       </tr>
     </table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-05]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain2.com</td>
     <td>22</td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain4.com</td>
     <td>10</td>
   </tr>
</table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-06]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain3.com</td>
     <td>20</td>
   </tr>
   <tr class="yellow">
     <td>domain4.com</td>
     <td>31</td>
   </tr>
   <tr class="yellow">
     <td>domain5.com</td>
     <td>32</td>
   </tr>
   <tr class="yellow">
     <td>domain6.com</td>
     <td>2</td>
   </tr>
   <tr class="yellow">
     <td>domain7.com</td>
     <td>17</td>
   </tr>
</table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-07]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
   </tr>
   <tr class="yellow">
     <td>domain1.com</td>
     <td>5</td>
   </tr>
   <tr class="yellow">
     <td>domain2.com</td>
     <td>3</td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain6.com</td>
     <td>12</td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
</table>
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter

.accent[2000-01-08]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain3.com</td>
     <td>10</td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain8.com</td>
     <td>22</td>
   </tr>
   <tr class="yellow">
     <td>domain9.com</td>
     <td>13</td>
   </tr>
</table> 
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter
.accent[2000-01-09]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain2.com</td>
     <td>5</td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain5.com</td>
     <td>3</td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain8.com</td>
     <td>2</td>
   </tr>
   <tr>
     <td><br></td>
     <td><br></td>
   </tr>
   <tr class="yellow">
     <td>domain10.com</td>
     <td>21</td>
   </tr>
   <tr class="yellow">
     <td>domain11.com</td>
     <td>14</td>
   </tr>   
</table> 

<!-----------------------------------#43-------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

<!-----------------------------------#44-------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

- solution 1 <br>
 visitors .accent[2000-01-04] +<br>
  visitors .accent[2000-01-05] +<br>
  visitors .accent[2000-01-06] +<br>
  visitors .accent[2000-01-07] <br>
<!-----------------------------------#45-------------------------------------->
---

class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]
<!-----------------------------------#45-------------------------------------->
---

class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-01]

  <table>
    <tr>
      <th>Domain</th>
      <th>visitors</th>
      <th>total</th>
    </tr>
    <tr class="yellow">
      <td>domain1.com</td>
      <td>1</td>
      <td>1</td>
    </tr>
  </table> 
  
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-02]

  <table>
    <tr>
      <th>Domain</th>
      <th>visitors</th>
      <th>total</th>
    </tr>
    <tr class="yellow">
      <td>domain1.com</td>
      <td>13</td>
      <td>14</td>
    </tr>
  </table> 
  
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-03]
  <table>
     <tr>
       <th>Domain</th>
       <th>visitors</th>
       <th>total</th>
     </tr>
     <tr class="yellow">
       <td>domain1.com</td>
       <td>6</td>
       <td>20</td>
     </tr>
     <tr class="yellow">
       <td>domain2.com</td>
       <td>3</td>
       <td>3</td>
     </tr>
     <tr class="yellow">
       <td>domain3.com</td>
       <td>3</td>
       <td>3</td>
     </tr>
   </table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-04]
  <table>
       <tr>
         <th>Domain</th>
         <th>visitors</th>
         <th>total</th>
       </tr>
       <tr class="yellow">
         <td>domain1.com</td>
         <td>1</td>
         <td>21</td>
       </tr>
       <tr class="yellow">
         <td>domain2.com</td>
         <td>3</td>
         <td>6</td>
       </tr>
       <tr class="yellow">
         <td>domain3.com</td>
         <td>2</td>
         <td>5</td>
       </tr>
       <tr class="yellow">
         <td>domain4.com</td>
         <td>1</td>
         <td>1</td>
       </tr>
     </table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-05]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
     <th>total</th>
   </tr>
   <tr>
     <td>domain1.com</td>
     <td>0</td>
     <td>21</td>
   </tr>
   <tr class="yellow">
     <td>domain2.com</td>
     <td>22</td>
     <td>28</td>
   </tr>
   <tr>
     <td>domain3.com</td>
     <td>0</td>
     <td>5</td>
   </tr>
   <tr class="yellow">
     <td>domain4.com</td>
     <td>10</td>
     <td>11</td>
   </tr>
</table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-06]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
     <th>total</th>
   </tr>
   <tr>
     <td>domain1.com</td>
     <td>0</td>
     <td>21</td>
   </tr>
   <tr>
     <td>domain2.com</td>
     <td>0</td>
     <td>28</td>
   </tr>
   <tr class="yellow">
     <td>domain3.com</td>
     <td>20</td>
     <td>25</td>
   </tr>
   <tr class="yellow">
     <td>domain4.com</td>
     <td>31</td>
     <td>42</td>
   </tr>
   <tr class="yellow">
     <td>domain5.com</td>
     <td>32</td>
     <td>32</td>
   </tr>
   <tr class="yellow">
     <td>domain6.com</td>
     <td>2</td>
     <td>2</td>
   </tr>
   <tr class="yellow">
     <td>domain7.com</td>
     <td>17</td>
     <td>17</td>
   </tr>
</table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-07]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
     <th>total</th>
   </tr>
   <tr class="yellow">
     <td>domain1.com</td>
     <td>5</td>
     <td>26</td>
   </tr>
   <tr class="yellow">
     <td>domain2.com</td>
     <td>3</td>
     <td>31</td>
   </tr>
   <tr>
     <td>domain3.com</td>
     <td>0</td>
     <td>25</td>
   </tr>
   <tr>
     <td>domain4.com</td>
     <td>0</td>
     <td>42</td>
   </tr>
   <tr>
     <td>domain5.com</td>
     <td>0</td>
     <td>32</td>
   </tr>
   <tr class="yellow">
     <td>domain6.com</td>
     <td>12</td>
     <td>14</td>
   </tr>
   <tr>
     <td>domain7.com</td>
     <td>0</td>
     <td>17</td>
   </tr>
</table>
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]

.accent[2000-01-08]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
     <th>total</th>
   </tr>
   <tr>
     <td>domain1.com</td>
     <td>0</td>
     <td>26</td>
   </tr>
   <tr>
     <td>domain2.com</td>
     <td>0</td>
     <td>31</td>
   </tr>
   <tr class="yellow">
     <td>domain3.com</td>
     <td>10</td>
     <td>35</td>
   </tr>
   <tr>
     <td>domain4.com</td>
     <td>0</td>
     <td>42</td>
   </tr>
   <tr>
     <td>domain5.com</td>
     <td>0</td>
     <td>32</td>
   </tr>
   <tr>
     <td>domain6.com</td>
     <td>0</td>
     <td>14</td>
   </tr>
   <tr>
     <td>domain7.com</td>
     <td>0</td>
     <td>17</td>
   </tr>
   <tr class="yellow">
     <td>domain8.com</td>
     <td>22</td>
     <td>22</td>
   </tr>
   <tr class="yellow">
     <td>domain9.com</td>
     <td>13</td>
     <td>13</td>
   </tr>
</table> 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Web page visitors counter .accent[with total]
.accent[2000-01-09]
<table>
   <tr>
     <th>Domain</th>
     <th>visitors</th>
     <th>total</th>
   </tr>
   <tr>
     <td>domain1.com</td>
     <td>0</td>
     <td>26</td>
   </tr>
   <tr class="yellow">
     <td>domain2.com</td>
     <td>5</td>
     <td>36</td>
   </tr>
   <tr>
     <td>domain3.com</td>
     <td>0</td>
     <td>35</td>
   </tr>
   <tr>
     <td>domain4.com</td>
     <td>0</td>
     <td>42</td>
   </tr>
   <tr class="yellow">
     <td>domain5.com</td>
     <td>3</td>
     <td>35</td>
   </tr>
   <tr>
     <td>domain6.com</td>
     <td>0</td>
     <td>14</td>
   </tr>
   <tr>
     <td>domain7.com</td>
     <td>0</td>
     <td>17</td>
   </tr>
   <tr class="yellow">
     <td>domain8.com</td>
     <td>2</td>
     <td>24</td>
   </tr>
   <tr>
     <td>domain9.com</td>
     <td>0</td>
     <td>13</td>
   </tr>
   <tr class="yellow">
     <td>domain10.com</td>
     <td>21</td>
     <td>21</td>
   </tr>
   <tr class="yellow">
     <td>domain11.com</td>
     <td>14</td>
     <td>14</td>
   </tr>   
</table> 

<!-----------------------------------#45-------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

- solution 1 <br>
 visitors .accent[2000-01-04] +<br>
 visitors .accent[2000-01-05] +<br>
 visitors .accent[2000-01-06] +<br>
 visitors .accent[2000-01-07] <br>
 
 
<!-----------------------------------#45-------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

- solution 1 <br>
 visitors .accent[2000-01-04] +<br>
 visitors .accent[2000-01-05] +<br>
 visitors .accent[2000-01-06] +<br>
 visitors .accent[2000-01-07] <br>
 
- solution 2 <br>
 total .accent[2000-01-07] - <br> 
 total .accent[2000-01-03] 
 

<!------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
]

#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

- solution 1 Monoid <br>
 visitors .accent[2000-01-04] combine<br>
 visitors .accent[2000-01-05] combine<br>
 visitors .accent[2000-01-06] combine<br>
 visitors .accent[2000-01-07] <br>
 
- solution 2 <br>
 total .accent[2000-01-07] combine ???<br> 
 total .accent[2000-01-03] 
 

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
]

Binary operation
 - .accent[closed] `x ∊ A, y ∊ A => x • y ∊ A` 
 - .accent[associative] `((x • y) • z) == (x • (y • z))`
 - .accent[identity element] `Ø, x • Ø == x == Ø • x`
 - .accent[inverse] `x • x' == Ø`

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
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

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
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
### - motivation
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
### - motivation
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

object Group {
  def apply[A](implicit group: Group[A]) = group
}
```
<!------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
]

#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

- solution 1 Monoid <br>
 visitors .accent[2000-01-04] combine<br>
 visitors .accent[2000-01-05] combine<br>
 visitors .accent[2000-01-06] combine<br>
 visitors .accent[2000-01-07] <br>
 
- solution 2 Group <br>
 total .accent[2000-01-07] combine <br> 
 inverse(total)  .accent[2000-01-03]
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
]

#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

- solution 1 Monoid <br>
 visitors .accent[2000-01-04] combine<br>
 visitors .accent[2000-01-05] combine<br>
 visitors .accent[2000-01-06] combine<br>
 visitors .accent[2000-01-07] <br>
 
- solution 2 Group <br>
 total .accent[2000-01-07] remove <br> 
 total .accent[2000-01-03]
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]
```scala
implicit val intGroup = new Group[Int] {
  def inverse(a: Int): Int = -a
  def empty: Int = 0
  def combine(x: Int, y: Int): Int = x + y
}

```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
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

```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
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
### - motivation
### - core
### - examples
]
```scala
implicit def numericGroup[A: Numeric] = new Group[A] {
  def inverse(a: A): A = Numeric[A].negate(a)
  def empty: A = Numeric[A].zero
  def combine(x: A, y: A): A = Numeric[A].plus(x, y)
}

```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
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
### - motivation
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
### - motivation
### - core
### - examples
]
```scala
implicit def mapGroup[K, V: Group]: Group[Map[K, V]] = new Group[Map[K, V]] {
  
  def inverse(a: Map[K, V]): Map[K, V] =
     a.view.mapValues(Group[V].inverse).toMap
      .filterNot { case (_, v) => Group[V].isEmpty(v) }

  def empty: Map[K, V] = Map.empty

  def combine(x: Map[K, V], y: Map[K, V]): Map[K, V] = {
    mapMergeSemigroup[K, V].combine(x, y)
      .filterNot { case (_, v) => Group[V].isEmpty(v) }
  }
}
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]

.mono[Map[K, V] <=> Set[(K, V)]]

https://martintupy.github.io/showcase/optics/optics.html#17
https://martintupy.github.io/showcase/optics/optics.html#20

```scala
def mapSetIso[K, V] = Iso[Map[K, V], Set[(K, V)]](_.toSet)(_.toMap)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]

```scala
implicit def setUnionGroup[A, K, V](
  implicit keyValueIso: Iso[A, (K, V)],
  valueGroup: Group[V]
): Group[Set[A]] = new Group[Set[A]] {
  
  def inverse(as: Set[A]): Set[A] =
    Group[Map[K, V]].inverse(as.map(keyValueIso.get).toMap)
      .map(keyValueIso.reverseGet _).toSet
  
  def empty: Set[A] = Set.empty[A]
  
  def combine(x: Set[A], y: Set[A]): Set[A] = {
    val xMap = x.map(keyValueIso.get).toMap
    val yMap = y.map(keyValueIso.get).toMap
    mapMergeSemigroup[K, V].combine(xMap, yMap)
      .map(keyValueIso.reverseGet _).toSet
  }
}
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]
#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

```scala
val map1 = Map( // 2000-01-04
  "domain1.com" -> 1, 
  "domain2.com" -> 3, 
  "domain3.com" -> 2,
  "domain4.com" -> 1
)

val map2 = Map( // 2000-01-05
  "domain2.com" -> 22,
  "domain4.com" -> 10
)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]
#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

```scala
val map3 = Map( // 2000-01-06
  "domain3.com" -> 20,
  "domain4.com" -> 31,
  "domain5.com" -> 32,
  "domain6.com" -> 2,
  "domain7.com" -> 17
)
val map4 = Map( // 2000-01-07
  "domain1.com" -> 5,
  "domain2.com" -> 3,
  "domain6.com" -> 12
)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]
#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

```scala
val visits = List(map1, map2, map3, map4)

import cats.instances.map._
import cats.instances.int._

Monoid[Map[String, Int]].combineAll(visits)
// domain1.com -> 6
// domain2.com -> 28
// domain3.com -> 22
// domain4.com -> 42
// domain5.com -> 32
// domain6.com -> 14
// domain7.com -> 17
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]
#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

```scala
val mapTotalBigger: Map[String, Int] =
  Map(
    "domain1.com" -> 26,
    "domain2.com" -> 31,
    "domain3.com" -> 25,
    "domain4.com" -> 42,
    "domain5.com" -> 32,
    "domain6.com" -> 14,
    "domain7.com" -> 17
  )

val mapTotalSmaller: Map[String, Int] = Map(
  "domain1.com" -> 20,
  "domain2.com" -> 3,
  "domain3.com" -> 3
)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]
#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

```scala
implicit def mapUnionGroup[K, V: Group]: Group[Map[K, V]] = ???

Group[Map[String, Int]].remove(mapTotalBigger, mapTotalSmaller)
// domain1.com -> 6
// domain2.com -> 28
// domain3.com -> 22
// domain4.com -> 42
// domain5.com -> 32
// domain6.com -> 14
// domain7.com -> 17
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]
#### Total visitors from .accent[2000-01-04] to .accent[2000-01-07] ?

```scala
implicit def mapUnionGroup[K, V: Group]: Group[Map[K, V]] = ???

Monoid[Map[String, Int]].combineAll(visits) == 
Group[Map[String, Int]].remove(mapTotalBigger, mapTotalSmaller)
// true
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]

```scala
val maps1: Map[Int, Map[String, Int]] = Map(
  1 -> Map("Adam" -> 10, "Monika" -> 3, "Anna" -> 6             ),
  2 -> Map("Adam" -> 2,  "Monika" -> 1,             "Maria" -> 5),
  3 -> Map(              "Monika" -> 3                          )
)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]

```scala
val maps1: Map[Int, Map[String, Int]] = Map(
  1 -> Map("Adam" -> 10, "Monika" -> 3, "Anna" -> 6             ),
  2 -> Map("Adam" -> 2,  "Monika" -> 1,             "Maria" -> 5),
  3 -> Map(              "Monika" -> 3                          )
)

val maps2: Map[Int, Map[String, Int]] = Map(
  1 -> Map("Adam" -> 10,                "Anna" -> 3             ),
  2 -> Map("Adam" -> 3                                          ),
  3 -> Map("Adam" -> 4,  "Monika" -> 1,             "Maria" -> 3)
)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]

```scala
val maps1: Map[Int, Map[String, Int]] = Map(
  1 -> Map("Adam" -> 10, "Monika" -> 3, "Anna" -> 6             ),
  2 -> Map("Adam" -> 2,  "Monika" -> 1,             "Maria" -> 5),
  3 -> Map(              "Monika" -> 3                          )
)

val maps2: Map[Int, Map[String, Int]] = Map(
  1 -> Map("Adam" -> 10,                "Anna" -> 3             ),
  2 -> Map("Adam" -> 3                                          ),
  3 -> Map("Adam" -> 4,  "Monika" -> 1,             "Maria" -> 3)
)

Group[Map[Int, Map[String, Int]]].remove(maps1, maps2)

```
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
]

```scala
val maps1: Map[Int, Map[String, Int]] = Map(
  1 -> Map("Adam" -> 10, "Monika" -> 3, "Anna" -> 6             ),
  2 -> Map("Adam" -> 2,  "Monika" -> 1,             "Maria" -> 5),
  3 -> Map(              "Monika" -> 3                          )
)

val maps2: Map[Int, Map[String, Int]] = Map(
  1 -> Map("Adam" -> 10,                "Anna" -> 3             ),
  2 -> Map("Adam" -> 3                                          ),
  3 -> Map("Adam" -> 4,  "Monika" -> 1,             "Maria" -> 3)
)

Group[Map[Int, Map[String, Int]]].remove(maps1, maps2)

//Map(
//1 -> Map(               Monika -> 3,  Anna -> 3               ),
//2 -> Map( Adam -> -1,   Monika -> 1,               Maria -> 5 ),
//3 -> Map( Adam -> -4,   Monika -> 2,               Maria -> -3)
//)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala
import cats.Group
import cats.instances.option._
import cats.kernel.Monoid

implicit def optionGroup[A: Group] = new Group[Option[A]] {

  def inverse(a: Option[A]): Option[A] = 
    a.map(Group[A].inverse).filterNot(Group[A].isEmpty)

  def empty: Option[A] = None

  def combine(x: Option[A], y: Option[A]): Option[A] =
    optionMonoid[A].combine(x, y).filterNot(Group[A].isEmpty)
}
```



<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
```
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
```
<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)

Option.empty[Int] remove Some(5) // Some(-5)
  
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)

Option.empty[Int] remove Some(5) // Some(-5)
  
import cats.syntax.option._ // syntax for option - helps compiler infer type
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)

Option.empty[Int] remove Some(5) // Some(-5)
  
import cats.syntax.option._ // syntax for option - helps compiler infer type
none[Int] remove 5.some // Some(-5)

```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)

Option.empty[Int] remove Some(5) // Some(-5)
  
import cats.syntax.option._ // syntax for option - helps compiler infer type
none[Int] remove 5.some // Some(-5)

5.some remove 5.some // None
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)

Option.empty[Int] remove Some(5) // Some(-5)
  
import cats.syntax.option._ // syntax for option - helps compiler infer type
none[Int] remove 5.some // Some(-5)

5.some remove 5.some // None
5.some remove none // Some(5)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)

Option.empty[Int] remove Some(5) // Some(-5)
  
import cats.syntax.option._ // syntax for option - helps compiler infer type
none[Int] remove 5.some // Some(-5)

5.some remove 5.some // None
5.some remove none // Some(5)
none[Int] remove 5.some // Some(-5)
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
]

```scala

import cats.instances.int._ // int instance for Group

optionGroup.remove(Option(5), Option(1)) // Some(4)
Group[Option[Int]].remove(Option(5), Option(1)) // Some(4)

import cats.syntax.group._ // syntax for group functions

Option(5) remove Option(1) // Some(4)
Option(5) |-| Option(1) // Some(4)
Option(5).inverse // Some(-5)

Option.empty[Int] remove Some(5) // Some(-5)
  
import cats.syntax.option._ // syntax for option - helps compiler infer type
none[Int] remove 5.some // Some(-5)

5.some remove 5.some // None
5.some remove none // Some(5)
none[Int] remove 5.some // Some(-5)
none[Int] remove none // None
```

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
### - laws
]

.mid.mono[
   combine(x, combine(y, z)) == <br> 
   combine(combine(x, y), z)
   
   <br>
   
   combine(x, empty) == <br>
   combine(empty, x) == <br>
   x
   
   <br>
   
   combine(x, inverse(x)) == <br>
   empty
]

<!-------------------------------------------------------------------------->
---
class: default

.left-column[
# Group
### - motivation
### - core
### - examples
### - cats
### - laws
]

#### .accent[!! This wasn't comutative group (Abelian Group)]

.mid.mono[
   combine(x, inverse(x)) .red[!=] <br>
   combine(inverse(x), x)
]

<!-------------------------------------------------------------------------->
---
class: center, middle, default
# That's it
#### If u will have any questions, issues with cats
#### (stack-overflows, not compiling code, etc.), 
#### hit me up on slack 🤛 
