# Async representation

- [back to Index](index.md)

## Future[A] 
- Future is scala standard representation of async computation, "something returned in future",
 while other computation can take advantage of multicore processors

- relies on underlying ExecutionContext which will provide and handle Threads

- contains built in failure represented as an Exception, 
  failures can be handled with methods `recover`, `recoverWith`, `transform` ...
  
- handling error is not force by the compiler, it always assumes that result will be returned correctly (otherwise error will be thrown)
          

## EitherT[Future, E, A]

- Either[E, Future[A]] - https://typelevel.org/cats/datatypes/eithert.html

- same as Future[A] but also contains possible error `E` in Left side of `EitherT`

- handling errors `E` is done by destructring EitherT (similarly to Either), 
 this will be forced by the compiler, because EitherT cannot be safely converted to Future
 


### Future vs EitherT

Decide for
 
1. Future
    - when async computation can represent just a general error, all errors are handled the same way
  
2. EitherT 
    - when async computation can end with one of the multiple error cases, which are handled respectively    
  