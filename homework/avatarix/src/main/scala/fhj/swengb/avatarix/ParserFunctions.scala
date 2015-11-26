package fhj.swengb.avatarix

import fhj.swengb.{Students, Student}

import scala.collection.mutable.Map
import scala.collection.mutable.MutableList

/**
  * Created by Daniel on 25.11.2015.
  */
object ParserFunctions {
  /**
    * List[Students]
    */

    //students will fail due to our programme can only display up to 12 users per load because the grid pane is set to 4x3
  val students = Students.mkStudents().toList
  val students1 = Students.studentGroup1.toList
  val students2 = Students.studentGroup2.toList
  val students3 = Students.studentGroup3.toList

  /**
    * gibt uns eine Map von Studenten mit den Parametern:
    * Key = UserID, Value = List(Firstname,Secondname, Githubusername, AvatarUrl)
    *
    * zur Auflistung aller Students als Overview
     */
  def getData(students:List[Student]):Map[String,List[String]] = {
    val data = Map[String,List[String]]()
    for(value <- students) {
      data += value.githubUsername -> List(value.firstName,
        value.secondName,
        value.userId,
        value.gitHubUser.avatarUrl.toString,
        value.gitHubUser.html.toString,
        value.gitHubUser.fing.toString,
        value.gitHubUser.fing_url.toString,
        value.gitHubUser.foll.toString,
        value.gitHubUser.foll_url.toString,
        value.gitHubUser.create.toString)
    }
    data
  }


  /***
    * TEST MAPS für getData()
    */
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

  val test2 = Map("wsteinkellner" -> List("Wolfgang", "Steinkellner", "steinkel14", """https://avatars.githubusercontent.com/u/15148418?v=3"""),
    "shysi" -> List("Steven", "Hysi", "Lionade", """https://avatars.githubusercontent.com/u/3136904?v=3"""),
    "mknaller" -> List("Markus", "Knaller", "knalla66", """https://avatars.githubusercontent.com/u/15108257?v=3"""),
    "vskerbinz" -> List("Verena", "Skerbinz", "verisker", """https://avatars.githubusercontent.com/u/15111302?v=3"""),
    "sleitner" -> List("Stefan", "Leitner", "loete", """https://avatars.githubusercontent.com/u/14996758?v=3"""),
    "clagger" -> List("Christian", "Lagger", "clagger", """https://avatars.githubusercontent.com/u/15108093?v=3"""),
    "abajric" -> List("Amar", "Bajric", "amarbajric", """https://avatars.githubusercontent.com/u/15001225?v=3"""),
    "ghoxha" -> List("Granit", "Hoxha", "hoxhagra14", """https://avatars.githubusercontent.com/u/15148184?v=3"""),
    "dkandlhofer" -> List("Daniel", "Kandlhofer", "danielkandlhofer", """https://avatars.githubusercontent.com/u/15106897?v=3"""),
    "ekeck" -> List("Elke", "Keck", "elkekeck", """https://avatars.githubusercontent.com/u/15153080?v=3"""),
    "gmeizenitsch" -> List("Georg", "Meizenitsch", "meizenit14", """https://avatars.githubusercontent.com/u/15153074?v=3"""),
    "jjohansson" -> List("Julia", "Johansson", "julia08", """https://avatars.githubusercontent.com/u/15020979?v=3"""))
}
