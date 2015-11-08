/*package fhj.swengb.simplecalc
//import _root_.scala.swing._
//import _root_.javax.swing._

/**
  * Calculator
  *
  * @author raam
  * @version 0.1
  */
object Calculator extends SimpleSwingApplication {

  val opList = List("+", "-", "/", "*", "%", "C")

  val regExp = "([0-9])".r

  def add(x: Float)(y: Float) = x + y

  def sub(x: Float)(y: Float) = x - y

  def mult(x: Float)(y: Float) = x * y

  def div(x: Float)(y: Float) = x / y

  def mod(x: Float)(y: Float) = x % y

  var f = add(0) _

  def txt = display.text match{
    case "" => 0
    case _ => display.text.toFloat
  }

  def opAction(fn: Float => Float) = { f = fn; display.text = "" }

  def handleClick(s: String) = s match {
    case "+" => opAction(add(txt) _)
    case "-" => opAction(sub(txt) _)
    case "*" => opAction(mult(txt) _)
    case "/" => opAction(div(txt) _)
    case "%" => opAction(mod(txt) _)
    case "C" => opAction(add(0) _)
    case "=" => display.text = f(txt).toString
  }

  class CalcAction(title0: String) extends swing.Action(title0) {
    override def apply = title0 match {
      case regExp(x) => display.text += x
      case "." => if(!display.text.contains(".")) display.text += title0
      case _ => handleClick(title0)
    }
  }

  class CalcButton(text0: String) extends Button(text0) { action = new CalcAction(text) }

  val numPanel = new MigPanel("wrap 3", "center", "center") {
    (1 to 9) foreach (x => contents += new CalcButton(x.toString))
    List("0", ".", "=") foreach (x => contents += new CalcButton(x.toString))
  }

  val operatorPanel = new MigPanel("wrap 2", "center", "center") {
    opList foreach (x => contents += new CalcButton(x))
  }

  val display = new TextField(25)

  def top = new MainFrame {
    title = "Simple Calculator"
    contents = new MigPanel("wrap 2", "center", "center") {
      contents += (display, "span 2")
      contents += numPanel
      contents += operatorPanel
    }
  }
} */