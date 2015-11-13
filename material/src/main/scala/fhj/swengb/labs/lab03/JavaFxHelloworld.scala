package fhj.swengb.labs.lab03

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{ImageView, Image}
import javafx.scene.layout.BorderPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import fhj.swengb.{Students, Speakers}

import scala.util.control.NonFatal

/**
  * A simple hello world example application
  * for JavaFX.
  */
object JavaFxHelloworld {
  def main(args: Array[String]) {
    Application.launch(classOf[JavaFxHelloworld], args: _*)
  }
}

class JavaFxHelloworld extends javafx.application.Application {


  val Fxml = "/fhj/swengb/lectures/l03/Helloworld.fxml"
  val Css = "fhj/swengb/lectures/l03/buttonstyles.css"

  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Helloworld")
      loader.load[Parent]() // side effect
      val scene = new Scene(loader.getRoot[Parent])
      stage.setScene(scene)
      stage.getScene.getStylesheets.add(Css)
      stage.show()
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

}


class HelloworldController extends Initializable {

  @FXML var seppl77 : BorderPane = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val url: String = Speakers.rladstaetter.gitHubUser.avatarUrl.toString

    //val url = Students.mfuchs.gitHubUser.avatarUrl.toString
    seppl77.setCenter(new ImageView(new Image(url)))
}

  def clicked(): Unit = {
    println("clicked")
  }

}