package fhj.swengb.avatarix

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.BorderPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import fhj.swengb.Speakers

import scala.util.control.NonFatal

object Avatarix {
  def main(args: Array[String]) {
    Application.launch(classOf[Avatarix], args: _*)
  }
}

class Avatarix extends javafx.application.Application {


  val Fxml = "/fhj/swengb/avatarix/Avatarix.fxml"
  val Css = "fhj/swengb/avatarix/Avatarix.css"

  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Avatarix")
      loader.load[Parent]() // side effect
      val scene = new Scene(loader.getRoot[Parent])
      stage.setScene(scene)
      //stage.getScene.getStylesheets.add(Css)
      stage.show()
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

}


class AvatarixController extends Initializable {
  @FXML var seppl77: BorderPane = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val url: String = Speakers.rladstaetter.gitHubUser.avatarUrl.toString
    //val url = Students.mfuchs.gitHubUser.avatarUrl.toString
    seppl77.setCenter(new ImageView(new Image(url)))
  }

}