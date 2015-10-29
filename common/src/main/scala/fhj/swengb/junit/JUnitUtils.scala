package fhj.swengb.junit

import scala.collection.immutable
import scala.xml.{Elem, Node}

/**
  * Classes and traits which make JUnit XML File parsing easier.
  */
sealed trait JUnitTest {
  def isSuccess: Boolean
}

case class JUnitFailure(message: String) extends JUnitTest {
  val isSuccess = false
}

case class JUnitSuccess(className: String, name: String, time: Double) extends JUnitTest {
  val isSuccess = true
}

object JUnitFailure {
  def apply(elem: Node): JUnitFailure = {
    val msg: String = (elem \ "@message").text
    JUnitFailure(msg)
  }
}


object JUnitTest {

  def apply(elem: Node): JUnitTest = {
    val className = (elem \ "@classname").text
    val name = (elem \ "@name").text
    val time = (elem \ "@time").text
    (elem \ "failure").map(JUnitFailure(_)).headOption match {
      case Some(f) => f
      case None => JUnitSuccess(className, name, time.toDouble)
    }
  }


}


object JUnitTestSuite {
  def apply(elem: Node): JUnitTestSuite = {
    val tests: immutable.Seq[JUnitTest] = (elem \ "testcase").map(JUnitTest(_))

    val attribTests = (elem \ "@tests").text.toInt
    assert(tests.size == attribTests)

    val name = (elem \ "@name").text
    val time = (elem \ "@time").text.toDouble
    val errors = (elem \ "@errors").text.toInt
    val skipped = (elem \ "@skipped").text.toInt
    JUnitTestSuite(name, time, errors, skipped, tests)
  }
}

case class JUnitTestSuite(name: String,
                          time: Double,
                          errors: Int,
                          skipped: Int,
                          testcases: Seq[JUnitTest]) {

  val (successes, failures) = testcases partition {
    t => t.isSuccess
  }

}