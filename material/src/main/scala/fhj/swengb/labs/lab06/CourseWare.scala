package fhj.swengb.labs.lab06

import java.io.File

import fhj.swengb.SwengbUtil

import scala.io.Source

/**
  * have a look into target/site dir
  *
  * interesting: http://www.webjars.org/
  */
object CourseWare {

  val target = new File("/Users/lad/Documents/fhj/fhj.swengb.course/material/target/site/example.html")

  def main(args: Array[String]) {
    val html = Source.fromInputStream(getClass.getResourceAsStream("example.html")).mkString
    SwengbUtil.writeToFile(target, html)
    println("Created " + target.getAbsolutePath)
  }

}
