package fhj.swengb.avatarix

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.BorderPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage


import fhj.swengb.{Student, Speakers, Students}

import scala.util.control.NonFatal
import scala.collection.mutable.Map

object Avatarix {
  def main(args: Array[String]) {
    //Application.launch(classOf[Avatarix], args: _*)

    /*
    wie man zugreifen kann:

  ParserFunctions.getStudentData()
  */
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
  @FXML var border_pane: BorderPane = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    //val url: String = Students.dkandlhofer.gitHubUser.avatarUrl.toString
    //val url = Students.mfuchs.gitHubUser.avatarUrl.toString

    //border_pane.setCenter(new ImageView(new Image(url)))
  }

}