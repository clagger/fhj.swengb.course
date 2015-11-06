package fhj.swenb.lectures.l03

import fhj.swengb.lectures.l03.SwengBList
import org.junit.Assert._
import org.junit.Test

/**
  * Created by lad on 02.11.15.
  */
class Lecture03Test {

  @Test def testSum(): Unit = {
    val l = SwengBList(1, 2, 3, 4)
    assertEquals(10, SwengBList.sum(l))
  }

  @Test def testProduct(): Unit = {
    val l = SwengBList(1.0, 2.0, 3.0, 4.0)
    assertEquals(24.0, SwengBList.product(l),0.01)
  }

}
