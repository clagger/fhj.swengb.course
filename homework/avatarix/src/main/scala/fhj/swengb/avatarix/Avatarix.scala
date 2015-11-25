package fhj.swengb.avatarix

import java.awt.event.MouseEvent
import java.beans.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{AnchorPane, GridPane, HBox, BorderPane}
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
  @FXML var main_pane: AnchorPane = _
  @FXML var grid_pane: GridPane = _


  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    var gridRow = 0
    var gridColumn = 0

    for (i <- ParserFunctions.test1) {
      val image: ImageView = new ImageView()
      image.setImage(new Image(i._2(3)))
      image.setFitHeight(120)
      image.setFitWidth(120)
      grid_pane.add(image, gridColumn, gridRow)

      if (gridColumn >= 3) {
        gridRow += 1
        gridColumn = 0
      }
      else gridColumn += 1
    }

  }




}
