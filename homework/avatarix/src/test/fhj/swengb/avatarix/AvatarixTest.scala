package fhj.swengb.avatarix

import java.awt.event.MouseEvent

import org.junit.Assert._
import org.junit.Test
import fhj.swengb.avatarix._

/**
 * Created by Steve on 27.11.2015.
 */
class AvatarixTest {
  @Test def TestLoader(): Unit = {
    val con = new AvatarixController()
    try{con.pictureLoader()}
    catch{
      case unknown => println("Could not load Map")
    }
  }

  @Test def TestgetData(): Unit = {
    val data = ParserFunctions.getData(ParserFunctions.students2)
    assertEquals("Bajric", data("amarbajric")(0))
    assertEquals("Amar", data("amarbajric")(1))
  }
}
