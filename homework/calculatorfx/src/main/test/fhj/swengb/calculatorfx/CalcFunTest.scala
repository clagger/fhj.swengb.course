package fhj.swengb.calculatorfx

import org.junit.Assert._
import org.junit.Test

/**
  * Created by lad on 07.11.15.
  */
class CalcFunTest {


  @Test def test1(): Unit = {
    assertEquals(4.0, CalcFun.mkNumber(List(4)), 0.0)
  }

  @Test def test2(): Unit = {
    assertEquals(1234.0, CalcFun.mkNumber(List(4, 3, 2, 1)), 0.0)
  }

  @Test def test3(): Unit = {
    assertEquals(0.0, CalcFun.mkNumber(List()), 0.0)
  }
}
