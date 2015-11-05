package fhj.swengb.exercises.ex02

import org.junit.Test

import scala.util.{Failure, Success, Try}

import org.junit.Assert._
/**
  * Created by lad on 29.10.15.
  */
class ExampleTest {

  def o(i: Int): Try[String] = Try {
    if (i == 0)
      i.toString
    else
      ???
  }

  def aString(i: Int): String = {
    if (i != 0)
      i.toString
    else
      null
  }


  @Test def test(): Unit = {
    o(10) match {
      case Success(asString) => println(asString)
      case Failure(e) => System.err.println(s"Whoops: ${e.getMessage}")
    }

    val someString: Option[String] = Option(aString(0))

    someString match {
      case Some(string) => string.charAt(0)
      case None => // exceptional case
    }

    for (s <- someString) println(s)

    o(10).map(s => println(s))
  }

}
