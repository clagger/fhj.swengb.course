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
    Application.launch(classOf[Avatarix], args: _*)

    /*
    wie man zugreifen kann:

  ParserFunctions.getStudentData()
  */
  }
}

class Avatarix extends javafx.application.Application {

  //alte fxml datei (vom ladstaetter)
  val FxmlALT = "/fhj/swengb/avatarix/Avatarix.fxml"
  //neue Fxml von (amar) Beta...
  val FxmlNEU = "/fhj/swengb/avatarix/Avatarix_Amar_Beta.fxml"
  val Css = "fhj/swengb/avatarix/Avatarix.css"

  val loader = new FXMLLoader(getClass.getResource(FxmlNEU))

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
  @FXML var main_pane : AnchorPane = _
  @FXML var grid_pane : GridPane = _


  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val url: String = "https://avatars0.githubusercontent.com/u/119250?v=3&s=400"
    val url2: String = "https://avatars3.githubusercontent.com/u/15001225?v=3&s=460"
    grid_pane.setHgap(10);
    grid_pane.setVgap(10);
    grid_pane.getChildren().add(new HBox(new ImageView(new Image(url))))
    grid_pane.getChildren().add(new HBox(new ImageView(new Image(url2))))

  }

}
