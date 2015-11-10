package fhj.swengb.calculatorfx

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.control.NonFatal

/**
  * A simple calculator for JavaFX using reverse polish notation
  */
object CalculatorFX {
  def main(args: Array[String]) {
    Application.launch(classOf[CalculatorFX], args: _*)
  }
}

class CalculatorFX extends javafx.application.Application {

  val FxmlAbajric = "/fhj/swengb/calculatorfx/calculatorfx.fxml"
  val CssAbajric = "fhj/swengb/calculatorfx/calculatorfx.css"

  val FxmlDeKilla = "/fhj/swengb/calculatorfx/calculatorfx_dekilla.fxml"
  val CssDeKilla = "fhj/swengb/calculatorfx/calculatorfx_dekilla.css"

  val DefaultFxml = FxmlDeKilla
  val DefaultCss = CssDeKilla

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("CalculatorFX")
      setSkin(stage, DefaultFxml, DefaultCss)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

  def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
    val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
    stage.setScene(scene)
    stage.getScene.getStylesheets.clear()
    stage.getScene.getStylesheets.add(css)
  }
}

object CalcFun {

  def mkNumber(reverseDigits: List[Int]): Double = {

    val base = 10

    def loop(l: List[Int], acc: Double, exp: Int): Double = {
      l match {
        case List() => acc
        case hd :: tl => loop(tl, hd * scala.math.pow(base, exp) + acc, exp + 1)
      }
    }
    loop(reverseDigits, 0, 0)
  }

}

/**
  * all supported operations should extend this trait
  */
sealed trait CalcOps

/**
  * tries to execute a 'plus' on the number stack
  */
case object PLUS extends CalcOps

/**
  * tries to execute a 'minus' on the number stack
  */
case object MINUS extends CalcOps


case object SGN extends CalcOps


case object MULTIPLY extends CalcOps

case object DIVIDE extends CalcOps

case object COMMA extends CalcOps

case object PERCENT extends CalcOps

case object CLEAR extends CalcOps

/**
  * puts the current digits onto the numbers stack
  */
case object ENTER extends CalcOps

// TODO implement other operations

class CalculatorFXController extends Initializable {

  @FXML var displayTextField: TextField = _

  var reverseDigits: List[Int] = List()

  var numbers: List[Double] = List()

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }

  def emit(i: Int): Unit = {
    reverseDigits = i :: reverseDigits
    displayTextField.setText(reverseDigits.reverse.mkString(""))
  }


  def plus(a: Double, b: Double): Double = a + b

  def updateDisplay(head: Double): Unit = {
    //displayTextField.setText(head.formatted("%f"))
    displayTextField.setText(head.toString)
  }

  def op(op: CalcOps): Unit = {
    op match {
      case SGN =>
        //println(numbers.head * -1)
        updateDisplay(numbers.head * -1)
      case ENTER =>
        numbers = mkNumber(reverseDigits) :: numbers
      case PLUS =>
        numbers = mkNumber(reverseDigits) :: numbers
        val a = numbers.head
        val b = numbers.tail.head
        println(plus(a, b))
        numbers = plus(a, b) :: numbers.tail.tail
      case MINUS => ???
      case MULTIPLY => ???
      case DIVIDE => ???
      case COMMA => ???
      case PERCENT => ???
      case CLEAR => ???
      case _ => ???
    }
    updateDisplay(numbers.head)
  }

  def mkNumber(revDigits: List[Int]): Double = {
    val number = CalcFun.mkNumber(revDigits)
    reverseDigits = List()
    number
  }

  def zero(): Unit = emit(0)

  def one(): Unit = emit(1)

  def two(): Unit = emit(2)

  def three(): Unit = emit(3)

  def four(): Unit = emit(4)

  def five(): Unit = emit(5)

  def six(): Unit = emit(6)

  def seven(): Unit = emit(7)

  def eight(): Unit = emit(8)

  def nine(): Unit = emit(9)

  def plus(): Unit = op(PLUS)

  def minus(): Unit = op(MINUS)

  def enter(): Unit = op(ENTER)

  def sgn(): Unit = op(SGN)

  def multiply(): Unit = op(MULTIPLY)

  def divide(): Unit = op(DIVIDE)

  def comma(): Unit = op(COMMA)

  def percent(): Unit = op(PERCENT)

  def clear(): Unit = op(CLEAR)

}

