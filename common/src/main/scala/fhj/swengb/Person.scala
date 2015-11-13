package fhj.swengb

import java.net.URL

import fhj.swengb.GitHub.User

/**
  * Created by lad on 24.09.15.
  */
sealed trait Person {

  def githubUsername: String

  def firstName: String

  def secondName: String

  def longName = s"$firstName $secondName"

  def normalize(in: String): String = {
    val mapping =
      Map("ä" -> "ae",
        "ö" -> "oe",
        "ü" -> "ue",
        "ß" -> "ss")
    mapping.foldLeft(in) { case ((s, (a, b))) => s.replace(a, b) }
  }

  /*
  def normalize2(input: String): String = {
    val output = Normalizer.normalize(input, Normalizer.Form.NFD)
    val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
    pattern.matcher(output).replaceAll("")
  } */


  def userId: String = {
    val fst = firstName(0).toLower.toString
    normalize(fst + secondName.toLowerCase)
  }

  val gitHubHome: String = s"https://github.com/$githubUsername/"

  @deprecated("remove", "now")
  val tutorialName: String = "fhj.swengb.assignments.tutorial"
  val tutorialURL: URL = new URL(gitHubHome + tutorialName)

  def mkHome: String = s" - $longName : [$githubUsername]($gitHubHome)"

  def gitHubUser: GitHub.User = {
    import GitHub.GithubUserProtocol._
    import GitHub._
    import spray.json._

    val webserviceString: String = scala.io.Source.fromURL(new URL(s"https://api.github.com/users/$githubUsername")).mkString
    webserviceString.parseJson.convertTo[User]
  }

}

case class Speaker(firstName: String,
                   secondName: String,
                   githubUsername: String,
                   groupId: Int) extends Person

case class Student(firstName: String,
                   secondName: String,
                   githubUsername: String,
                   group: Int) extends Person
