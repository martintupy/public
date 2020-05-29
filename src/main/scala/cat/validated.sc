import cats.data.Validated

Validated.Valid(1).andThen(a => Validated.Valid(1 + a))