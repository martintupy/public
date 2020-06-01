#Traverse FAQ

### Isn't Traverse parallel ? 

traverse is not spawning new threads, every value in `Traverse` is processed sequentially 

```scala mdoc
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

def effect(n: Int): Future[Unit] = {
  Thread.sleep(1000 / n) // bigger the number, shorter it takes
  Future(println(n))
}

def program = (1 to 10).toList.traverse(effect)


Await.result(program, 1.minute)
```

### Why cats uses only List and Vector instead of Seq ?

A Seq is an Iterable that has a defined order of elements. Seq has many subclasses:
![Seq](./img/seq.png)

It would be too much to write and include a `Traverse` instance in cats library for every Seq's subclass

List will suffice for general purpose (I would say 99%)

there is also:
- `Vector` instance for Traverse if performance of indexed accessing is important, 
- [Chain](https://typelevel.org/cats/datatypes/chain.html) - cat's more performant "Seq like" implementation for Monoidal use and (constant time for prepending)
- [NonemptyList](https://typelevel.org/cats/datatypes/nel.html) - when empty List isn't allowed (is considered as a special case) *very useful data type



Looking at the cat's implementation of Traverse

- for List
```scala
def traverse[G[_], A, B](fa: List[A])(f: A => G[B])(implicit G: Applicative[G]): G[List[B]] =
  foldRight[A, G[List[B]]](fa, Always(G.pure(List.empty))) { (a, lglb) =>
    G.map2Eval(f(a), lglb)(_ :: _)
  }.value
```

- for Vector
```scala
override def traverse[G[_], A, B](fa: Vector[A])(f: A => G[B])(implicit G: Applicative[G]): G[Vector[B]] =
  foldRight[A, G[Vector[B]]](fa, Always(G.pure(Vector.empty))) { (a, lgvb) =>
    G.map2Eval(f(a), lgvb)(_ +: _)
  }.value
```

- for Chain 
- for NonEmptyList

it's obvious what would be type of empty sequence (List, Vector, Chain, NonEmptyList respectively)

but for Seq we don't know what will be the representation

trying to implement Traverse for Seq would end up with something like 

```scala
override def traverse[G[_], A, B](fa: Seq[A])(f: A => G[B])(implicit G: Applicative[G]): G[Seq[B]] =
  foldRight[A, G[Seq[B]]](fa, Always(G.pure(Seq.empty))) { (a, lgvb) =>
    G.map2Eval(f(a), lgvb)(_ +: _)
  }.value
```

where `Seq.empty` is always `List` (therefore List is most of the cases inferred type)

```scala mdoc
Seq.empty
```