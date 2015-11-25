package fhj.swengb.avatarix


import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{AnchorPane, GridPane, HBox, BorderPane}
import javafx.scene.text.Text
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import fhj.swengb.{Students, Speakers}

import scala.util.control.NonFatal

object Avatarix {
  def main(args: Array[String]) {
    Application.launch(classOf[Avatarix], args: _*)
  }
}

class Avatarix extends javafx.application.Application {


  val Fxml = "/fhj/swengb/avatarix/lagger_testgui.fxml"
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
  @FXML var main_pane : AnchorPane = _
  @FXML var grid_pane : GridPane = _
  @FXML var gitHubUser : Text = _
  @FXML var githublink : Text = _
  @FXML var vorname : Text = _
  @FXML var nachname : Text = _
  @FXML var follower : Text = _
  @FXML var following : Text = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    //val url: String = "https://avatars0.githubusercontent.com/u/119250?v=3&s=400"
    //val url2: String = "https://avatars3.githubusercontent.com/u/15001225?v=3&s=460"
    grid_pane.setHgap(10);
    grid_pane.setVgap(10);
    //grid_pane.getChildren().add(new HBox((new ImageView(new Image(url)))))
    //override def iterate()
    var gridRow = 0
    var gridColumn = 0
    for (i <- ParserFunctions.test1) {
      val iv:ImageView = new ImageView()
      iv.setImage(new Image(i._2(3)))
      iv.setFitHeight(120)
      iv.setFitWidth(120)

      iv.setId(i._2(2))
     //iv.setUserData(i._2)

      grid_pane.add(iv, gridColumn, gridRow)

      iv.setOnMouseClicked(mouseEventHandler)

      gridColumn += 1
      if (gridColumn >= 3) {
        gridRow += 1
        gridColumn = 0
      }
    }
  }

  val mouseEventHandler: EventHandler[_ >: MouseEvent] = new EventHandler[MouseEvent] {
    override def handle(event: MouseEvent): Unit = {
      event.getSource match {
        case a: ImageView => {
          val studenlist = Students.studentGroup1.toList
          val data:List[String] = ParserFunctions.getStudentData(studenlist, a.getId)


          gitHubUser.setText(a.getId())
          //vorname.setText(data(0))
          //nachname.setText(data(0))
          //follower.setText(data(0))
          //following.setText(data(0))
        }


        case _ => assert(false)
      }
    }
  }


}
