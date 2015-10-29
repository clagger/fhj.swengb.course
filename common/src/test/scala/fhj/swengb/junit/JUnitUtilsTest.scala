package fhj.swengb.junit

import org.junit.Assert._
import org.junit.Test

import scala.xml.Elem


/**
  * Created by lad on 25.09.15.
  */
class JUnitUtilsTest {

  val failure: Elem =
    <failure message="expected" type="org.junit.ComparisonFailure">
      stacktrace
    </failure>

  val failedTestCase: Elem =
    <testcase classname="a.b.CTest" name="test2" time="0.001">
      {failure}
    </testcase>

  val successTestCase: Elem =
      <testcase classname="a.b.CTest" name="test2" time="0.001"/>

  val junitTestSuite: Elem =
    <testsuite tests="1"
               failures="1"
               name="a.b.CTest"
               time="0.001" errors="0" skipped="0">
      {failedTestCase}
    </testsuite>

  @Test def jUnitTestSuiteWorks(): Unit = {
    assertEquals(JUnitTestSuite("a.b.CTest", 0.001, 0, 0, List(JUnitFailure("expected"))), JUnitTestSuite(junitTestSuite))
  }

  @Test def partitioningWorks(): Unit = {
    val suite: JUnitTestSuite = JUnitTestSuite(junitTestSuite)
    assertEquals(List(JUnitFailure("expected")), suite.failures)
    assertEquals(List(), suite.successes)
  }


  @Test def jUnitFailureWorks(): Unit = {
    assertEquals(JUnitFailure("expected"), JUnitFailure(failure))
  }

  @Test def jUnitTestCaseWorks(): Unit = {
    assertEquals(JUnitSuccess("a.b.CTest", "test2", 0.001), JUnitTest(successTestCase))
  }

  @Test def testTests(): Unit = {
    assertEquals(1, JUnitTestSuite(junitTestSuite).failures.size)
  }


}


