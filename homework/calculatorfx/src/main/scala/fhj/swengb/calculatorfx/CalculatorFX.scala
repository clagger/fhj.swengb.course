package fhj.swengb.calculatorfx

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.collection.immutable.Stack
import scala.util.control.NonFatal

/**
  * A simple calculator for JavaFX
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
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

}


class CalculatorFXController extends Initializable {

  @FXML var displayTextField: TextField = _

  var list: Seq[String] = Seq()
  var stack : Stack[String] = Stack()


  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }

  def btn1(): Unit = {
    displayTextField.setText("jo this is button 1")
    list = list ++ Seq("1")
  }

  def btn2(): Unit = {
    displayTextField.setText("jo this is button 2")
    list = list ++ Seq("2")
  }


  def showList(): Unit = {
    displayTextField.setText(list.mkString(","))
  }





}