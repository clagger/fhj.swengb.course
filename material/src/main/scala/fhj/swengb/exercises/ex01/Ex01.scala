package fhj.swengb.exercises.ex01

/**
 * Created by lad on 15.10.15.
 */
// a simple class
class A(value: Boolean)

// a simple case class
case class B(value: String)

// an object (comparable to a singleton (see https://en.wikipedia.org/wiki/Singleton_pattern)
object C {

  val range = Seq(1, 2, 3, 4)

  // try to improve the implementation using idiomatic scala functions
  val productOfRange = range.foldLeft(1)((a, b) => a * b)

}