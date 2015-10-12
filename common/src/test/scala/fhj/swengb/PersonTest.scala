package fhj.swengb

import org.junit.Assert._
import org.junit.Test

class PersonTest {

  @Test def testRladstaetter(): Unit = {
    assertEquals("rladstaetter", People.rladstaetter.userId)
  }

  @Test def testSpecialchars(): Unit = {
    val s = Student("Robert", "Ladstätter", "awesomecoder", 0)
    assertEquals("rladstaetter", s.userId)
  }


}