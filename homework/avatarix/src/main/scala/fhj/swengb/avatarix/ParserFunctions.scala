package fhj.swengb.avatarix

import fhj.swengb.{Students, Student}

import scala.collection.mutable.Map

/**
  * Created by Daniel on 25.11.2015.
  */
object ParserFunctions {
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
  val valuetest = "deKilla"
  val studenlist = Students.studentGroup1.toList
  //val students1 = getData(Students.studentGroup1.toList)

  //println(getStudentData(studenlist,valuetest))


  /*
  Aufteilung der Studenten in Gruppen
   */

  //val students2 = getData(Students.studentGroup2.toList)
  //val students3 = getData(Students.studentGroup3.toList)

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
  //println(students2)
  //println(students3)
  //println(test1)


}
