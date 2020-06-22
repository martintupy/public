# Async representation

- [back to Index](index.md)

## Future[A] 
- Future is scala standard representation of async computation, "something returned in future",
 while other computation can take advantage of multicore processors

- relies on underlying ExecutionContext which will provide and handle Threads

- contains built in failure represented as an Exception, 
  failures can be handled with methods `recover`, `recoverWith`, `transform` ...
  
- handling error is not forced by the compiler, it always assumes that result will be returned correctly (otherwise error will be thrown)
          
 
## IO[A]

- https://typelevel.org/cats-effect/datatypes/io.html

- pure data type that express sync/async computation 

- lazy, composable, stack safe  
 
 
|              |        Eager        |            Lazy           |
|:------------:|:-------------------:|:-------------------------:|
|  Synchronous |          A          |          () => A          |
|              |                     |          Eval[A]          |
| Asynchronous | (A => Unit) => Unit | () => (A => Unit) => Unit |
|              |      Future[A]      |           IO[A]           |
 
- IO is explicit about everything, won't do anything until being told

```scala
val io = IO { println("I am IO") } // just instance containing a computation

io.unsafeRunSync()
// I am IO

io.start // start on a new "Green Thread", nothing happens until unsafe method
 
io.start.unsafeRunAsyncAndForget()
// I am IO


val delayedIO = IO { Thread.sleep(1000); println("I am IO") }

delayedIO.start.unsafeRunAsyncAndForget()
println("After unsafe async run")
// After unsafe async run
// I am IO
 
``` 
 
- side effects are not evaluated on instanciating IO type (they are suspended until call of unsafe method, this will preserve composability of computation in a pure way)
 
```scala
val io = IO { println("I am IO") }

val future = Future { println("I am Future") }
// I am Future

io.unsafeRunAsyncAndForget()
// I am IO
```  

## EitherT[F, E, A]

- F[Either[E, A]] - https://typelevel.org/cats/datatypes/eithert.html

- same as F[A] but also contains possible error `E` in Left side of `EitherT`

- handling errors `E` is done by destructring EitherT (similarly to Either), 
 this will be forced by the compiler, because EitherT cannot be safely converted to result value `A`

- EitherT doesn't care about `F` (as long as F is Applicative)

- e.g. `EitherT[Future, E, A]`, `EitherT[IO, E, A]`, ...
  

### Future vs EitherT vs IO

Decide for
 
1. Future
    - when async computation can represent just a general error, all errors are handled the same way    
    
2. IO
    - if you need more control over which Thread pool should be used

2. EitherT (IO, Future, ...)
    - when async computation can end with one of the multiple error cases, which are handled respectively              
  