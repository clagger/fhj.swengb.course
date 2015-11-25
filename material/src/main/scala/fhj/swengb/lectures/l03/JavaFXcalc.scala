package fhj.swengb.lectures.l03

/**
  * Created by Stefan Leitner on 06.11.2015.
  */
import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage
import javafx.scene._

import scala.util.control.NonFatal

/**
  * A calculator application for JavaFX.
  */
object JavaFXcalc {
  def main(args: Array[String]) {
    Application.launch(classOf[JavaFXcalc], args: _*)
  }
}

class JavaFXcalc extends javafx.application.Application {

  val Fxml = "/fhj/swengb/lectures/l03/calc.fxml"
  val Css = "fhj/swengb/lectures/l03/buttonstyles.css"

  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Calculator")
      loader.load[Parent]() // side effect
      val scene = new Scene(loader.getRoot[Parent])
      stage.setScene(scene)
      stage.getScene.getStylesheets.add(Css)
      stage.show()
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

}

class CalcController extends Initializable {

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  var display = "TEst"
  }
  def display(display):Unit = {
    display.setText
  }

  def btn_c(): Unit = {
    println("C")
  }

  def btn_square(): Unit = {
    println("^2")
  }

  def btn_percent(): Unit = {
    println("%")
  }

  def btn_multi(): Unit = {
    println("multi")
  }

  def btn_7(): Unit = {
    println("7")
  }

  def btn_8(): Unit = {
    println("8")
  }

  def btn_9(): Unit = {
    println("9")
  }

  def btn_div(): Unit = {
    println("/")
  }

  def btn_4(): Unit = {
    println("4")
  }

  def btn_5(): Unit = {
    println("5")
  }

  def btn_6(): Unit = {
    println("6")
  }

  def btn_minus(): Unit = {
    println("-")
  }

  def btn_1(): Unit = {
    println("1")
  }

  def btn_2(): Unit = {
    println("2")
  }

  def btn_3(): Unit = {
    println("3")
  }

  def btn_plus(): Unit = {
    println("+")
  }

  def btn_0(): Unit = {
    println("0")
  }

  def btn_comma(): Unit = {
    println(".")
  }

  def btn_equal(): Unit = {
    println("=")
  }

}
