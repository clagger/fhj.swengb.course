package fhj.swengb

import org.junit.Assert._
import org.junit.Test

class PersonTest {

  @Test def testRladstaetter(): Unit = {
    assertEquals("rladstaetter", People.rladstaetter.userId)
  }

  @Test def testHansMueller(): Unit = {
    val s = Student("Hans", "Müller", "awesomecoder", 0)
    assertEquals("hmueller", s.userId)
  }


}