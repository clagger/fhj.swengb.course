package fhj.swengb.exercises.ex01

import org.junit.Test
import org.junit.Assert._

/**
 * Created by lad on 15.10.15.
 */
class Ex01Test {

  // shows how to instantiate a class
  @Test def testInstantiateClass(): Unit = {
    assertNotNull(new A(true))
  }

  @Test def testInstantiateCaseClass(): Unit = {
    assertNotNull(B("hello"))
  }

  @Test def testRange() : Unit = {
    assertEquals(Seq(1,2,3,4), C.range)
  }

  @Test def testProduct() : Unit = {
    assertEquals(24, C.productOfRange)
  }


}
