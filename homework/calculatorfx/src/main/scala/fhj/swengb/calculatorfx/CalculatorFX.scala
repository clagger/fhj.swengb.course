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

  val FxmlAbajric = "/fhj/swengb/calculatorfx/calculatorfx_abajric.fxml"
  val CssAbajric = "fhj/swengb/calculatorfx/calculatorfx_abajric.css"

  val FxmlDeKilla = "/fhj/swengb/calculatorfx/calculatorfx_dekilla.fxml"
  val CssDeKilla = "fhj/swengb/calculatorfx/calculatorfx_dekilla.css"

  val DefaultFxml = FxmlAbajric
  val DefaultCss = CssAbajric

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

/**
  * tries to execute a 'multiplication' on the number stack
  */
case object MULTIPLICATION extends CalcOps

/**
  * tries to execute a 'division' on the number stack
  */
case object DIVISION extends CalcOps

/**
  * tries to execute a 'sign change' on the number stack
  */
case object SGN extends CalcOps

/**
  * tries to execute a 'percentage calculation' on the number stack
  */
case object PERCENTAGE extends CalcOps

/**
  * puts the current digits onto the numbers stack
  */
case object ENTER extends CalcOps

/**
  * clears screen and deletes the values in the list
  */
case object CLEAR extends CalcOps

/**
  * implements exponential calculation of the type x to the power of y
  */
case object POWER extends CalcOps

/**
  * implements exponential calculation of the type x²
  */
case object SQUARE extends CalcOps

/**
  * implements the calculation of 1 divided by a specific number
  */
case object ONEDIVX extends CalcOps

/**
  * implements the calculation of the square root of a number
  */
case object ROOT extends CalcOps


/**
  * creates a double by using a comma
  */
case object COMMA extends CalcOps


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

  def minus(a: Double, b: Double): Double = b - a

  def multiply(a: Double, b: Double): Double = a * b

  def divide(a: Double, b: Double): Double = b / a

  def percent(a: Double, b: Double): Double = a * (b/100)

  def sgn (a: Double) = a * -1.0

  def power(a: Double, exp: Double) = math.pow(a, exp)

  def square(a: Double) = math.pow(a, 2.0)

  def root(a: Double) = math.sqrt(a)

  def onedivx(a: Double) = 1/a

  def updateDisplay(head: Double): Unit = {
    displayTextField.setText(head.formatted("%f"))
    //displayTextField.setText(head.toString)

  }

  def op(op: CalcOps): Unit = {
    try {
      op match {
        case SGN =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.tail.head
          numbers = sgn(a) :: numbers.tail.tail
          println(numbers)
        case ENTER =>
          numbers = mkNumber(reverseDigits) :: numbers
        case PLUS =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.head
          val b = numbers.tail.head
          numbers = plus(a, b) :: numbers.tail.tail
          println(numbers)
        case MINUS =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.head
          val b = numbers.tail.head
          numbers = minus(a, b) :: numbers.tail.tail
        case MULTIPLICATION =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.head
          val b = numbers.tail.head
          numbers = multiply(a, b) :: numbers.tail.tail
        case DIVISION =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.head
          val b = numbers.tail.head
          if (numbers.head == 0.0) {
            throw new IllegalArgumentException
          }
          else {
            numbers = divide(a, b) :: numbers.tail.tail
          }
        case PERCENTAGE =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.head
          val b = numbers.tail.head
          numbers = percent(a, b) :: numbers.tail.tail
        case CLEAR =>
          numbers = (mkNumber(reverseDigits) :: numbers).diff(numbers)
        case POWER =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.tail.head
          val b = numbers.head
          numbers = power(a, b) :: numbers.tail.tail
        case SQUARE =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.tail.head
          numbers = square(a) :: numbers.tail.tail
        case ROOT =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.tail.head
          numbers = root(a) :: numbers.tail.tail
        case ONEDIVX =>
          numbers = mkNumber(reverseDigits) :: numbers
          val a = numbers.tail.head
          numbers = onedivx(a) :: numbers.tail.tail

        case COMMA => ???
        case _ => updateDisplay(numbers.head) //show last input
      }
      updateDisplay(numbers.head)
    }catch{
      case ex: IllegalArgumentException =>
        displayTextField.setText("No division by zero")
    }
  }

  def mkNumber(revDigits: List[Int]): Double = {
    val number = CalcFun.mkNumber(revDigits)
    reverseDigits = List()
    number
  }

  def btn0(): Unit = emit(0)

  def btn1(): Unit = emit(1)

  def btn2(): Unit = emit(2)

  def btn3(): Unit = emit(3)

  def btn4(): Unit = emit(4)

  def btn5(): Unit = emit(5)

  def btn6(): Unit = emit(6)

  def btn7(): Unit = emit(7)

  def btn8(): Unit = emit(8)

  def btn9(): Unit = emit(9)

  def plus(): Unit = op(PLUS)

  def minus(): Unit = op(MINUS)

  def multiply(): Unit = op(MULTIPLICATION)

  def divide(): Unit = op(DIVISION)

  def percent(): Unit = op(PERCENTAGE)

  def enter(): Unit = op(ENTER)

  def sgn(): Unit = op(SGN)

  def clear(): Unit = op(CLEAR)

  def comma(): Unit = op(COMMA)

  def power(): Unit = op(POWER)

  def square(): Unit = op(SQUARE)

  def root(): Unit = op(ROOT)

  def onedivx(): Unit = op(ONEDIVX)

}

