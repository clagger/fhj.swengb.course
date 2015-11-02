package fhj.swengb.exercises.ex02

import scala.util.Random

/**
  * An example for working with api's which can return null
  */
object AnObject {

  /**
    * an example of an unsafe method in respect to returning null.
    *
    * @return a string or null
    */
  def funWhichReturnsString(): String = {
    if (Random.nextBoolean())
      "asdf"
    else
      null
  }

  def exampleFun(): Unit = {
    Option(funWhichReturnsString()) match {
      case Some(string) => println("was success: " + string)
      case None => println("returned null")
    }
  }

  def workWithUnsafeMethods(): Unit = {
    Option(funWhichReturnsString()).map(println)
  }


  def main(args: Array[String]) {
    workWithUnsafeMethods()
  }

}
