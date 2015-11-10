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

  val Fxml = "/fhj/swengb/calculatorfx/calculatorfx.fxml"
  val Css = "fhj/swengb/calculatorfx/calculatorfx.css"

  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("CalculatorFX")
      loader.load[Parent]() // side effect
      val scene = new Scene(loader.getRoot[Parent])
      stage.setScene(scene)
      stage.getScene.getStylesheets.add(Css)
      stage.show()
      stage.setMinWidth(stage.getWidth());
      stage.setMinHeight(stage.getHeight());
    } catch {
      case NonFatal(e) => e.printStackTrace()
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
    displayTextField.setText(head.formatted("%f"))
  }

  def op(op: CalcOps): Unit = {
    op match {
      case ENTER =>
        numbers = mkNumber(reverseDigits) :: numbers
      case PLUS =>
        numbers = mkNumber(reverseDigits) :: numbers
        val a = numbers.head
        val b = numbers.tail.head
        numbers = plus(a, b) :: numbers.tail.tail
      case MINUS => ???
      case _ => ???
    }
    updateDisplay(numbers.head)
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

  def enter(): Unit = op(ENTER)


}

