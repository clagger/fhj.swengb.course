package fhj.swengb.lectures.l03


/**
  * In the declaration trait SwengBList[+A] the '+' in front of the type
  * parameter A is a variance annotation that signals taht A is a covariant
  * or "positive" parameter of SwengBList.
  *
  * This means: for instance a List[Dog] is considered a subtype of List[Animal],
  * always assuming that a Dog is a subtype of Animal.
  *
  * @tparam A
  */
sealed trait SwengBList[+A]

// 'Nothing' is a subtype of every other type
case object SwengBNil extends SwengBList[Nothing]

case class SwengBCons[+A](a: A, tail: SwengBList[A]) extends SwengBList[A]

// companion object
object SwengBList {

  def sum(l: SwengBList[Int]): Int =
    l match {
      case SwengBNil => 0
      case SwengBCons(hd, tl) => hd + sum(tl)
    }

  def product(l: SwengBList[Double]): Double = {
    l match {
      case SwengBNil => 1.0
      case SwengBCons(0.0, _) => 0.0
      case SwengBCons(hd, tl) => hd * product(tl)
    }
  }

  def apply[A](as: A*): SwengBList[A] = {
    if (as.isEmpty) SwengBNil
    else SwengBCons(as.head, apply(as.tail: _*))
  }

}
