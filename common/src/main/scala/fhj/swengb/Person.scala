package fhj.swengb

import java.text.Normalizer
import java.util.regex.Pattern

/**
 * Created by lad on 24.09.15.
 */
sealed trait Person {

  def gitHubUserName: String

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

}

case class Speaker(firstName: String, secondName: String, gitHubUserName: String, groupId: Int) extends Person

case class Student(firstName: String, secondName: String, gitHubUserName: String, group: Int) extends Person
