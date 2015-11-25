package fhj.swengb.avatarix

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{AnchorPane, HBox, GridPane, BorderPane}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage


import fhj.swengb.{Student, Speakers, Students}

import scala.util.control.NonFatal
import scala.collection.mutable.Map

object Avatarix {
  def main(args: Array[String]) {
    Application.launch(classOf[Avatarix], args: _*)

    /*
    gibt uns eine Map von Studenten mit den Parametern: Key = UserID, Value = List(Firstname,Secondname, Githubusername, AvatarUrl)
    zur Auflistung aller Students als Overview
     */
    def getData(students:List[Student]):Map[String,List[String]] = {
      val data = Map[String,List[String]]()
      for(value <- students) {
        data += value.userId -> List(value.firstName,
          value.secondName,
          value.githubUsername,
          value.gitHubUser.avatarUrl.toString
        )
      }
      data
    }


    /*
    gibt Detaildaten von ausgewählten Students in einer Liste zurück
    für Detailansicht
     */
    def getStudentData(students:List[Student],x:String):List[String] = {
      val res = Nil
      for(value <- students if(value.githubUsername == x)) {
        res ++ List(value.gitHubUser.html.toString,
          value.gitHubUser.fing.toString,
          value.gitHubUser.fing_url.toString,
          value.gitHubUser.foll.toString,
          value.gitHubUser.foll_url.toString,
          value.gitHubUser.create.toString)
      }
      res
    }


    /*
    Aufteilung der Studenten in Gruppen
     */
    //val students1 = getData(Students.studentGroup1.toList)
    val students2 = getData(Students.studentGroup2.toList)
    val students3 = getData(Students.studentGroup3.toList)

    val test1 = Map("fgraf" -> List("Felix", "Graf", "Graf-Carello", """https://avatars.githubusercontent.com/u/15038288?v=3"""),
      "ekarimova" -> List("Elza", "Karimova", "elsakarimova", """https://avatars.githubusercontent.com/u/15157578?v=3"""),
      "pkoerner" -> List("Paul", "Körner", "McKorleone", """https://avatars.githubusercontent.com/u/14877839?v=3"""),
      "thasenbichler" -> List("Timo", "Hasenbichler", "timoooo", """https://avatars.githubusercontent.com/u/15030831?v=3"""),
      "mfuchs" -> List("Michael", "Fuchs", "deKilla", """https://avatars.githubusercontent.com/u/4746687?v=3"""),
      "aschneider" -> List("Andreas", "Schneider", "Zerberuss", """https://avatars.githubusercontent.com/u/15108282?v=3"""),
      "jblazevic" -> List("Josip", "Blazevic", "jbtastic", """https://avatars.githubusercontent.com/u/15108227?v=3"""),
      "alichtenegger" -> List("Alexander", "Lichtenegger", "AlexanderLichtenegger", """https://avatars.githubusercontent.com/u/15108218?v=3"""),
      "cfuerbahs" -> List("Christoph", "Fürbahs", "furchr", """https://avatars.githubusercontent.com/u/12102333?v=3"""),
      "cherzog" -> List("Carina", "Herzog", "carinaher", """https://avatars.githubusercontent.com/u/15108186?v=3"""))
    //val test2 = Map()

    //println(students1)
    println(students2)
    println(students3)
    println(test1)

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
