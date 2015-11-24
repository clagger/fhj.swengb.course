package fhj.swengb.avatarix

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{AnchorPane, HBox, GridPane, BorderPane}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import fhj.swengb.Speakers
import fhj.swengb.Students

import scala.util.control.NonFatal

object Avatarix {
  def main(args: Array[String]) {
    Application.launch(classOf[Avatarix], args: _*)
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

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val url: String = "https://avatars0.githubusercontent.com/u/119250?v=3&s=400"
    val url2: String = Students.abajric.gitHubUser.avatarUrl.toString
    //grid_main_pane.add(new ImageView(new Image(url)),0,0)
    main_pane.getChildren().addAll(new HBox(5)(url))

  }

}