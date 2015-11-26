package fhj.swengb.assessment

import java.io.File
import java.net.URL


import fhj.swengb.{Student, SwengbUtil}
import fhj.swengb.{SwengbUtil, Student, Students}


import scala.io.Source
import scala.util.Try


case class Assessment(name: String, student: Student) {

  /**
    * the Name with uppercase first letter
    *
    * for example, "tutorial" will be "Tutorial"
    */
  val Name: String = name(0).toUpper + name.drop(1)

  /**
    * the assessment name
    */
  val assessmentName: String = "fhj.swengb.assignments." + name

  /**
    * We create a function to generate a report in the Markdown Format
    * about the status of the tutorial assignment.
    *
    * First, we need a function to fetch data from the Internet. It
    * should handle error cases too.
    *
    * It returns a string representation of the result.
    */
  def fetchFromInternet(url: URL): Try[String] = Try(SwengbUtil.fetch(url))

  /**
    * the url for the assignment
    */
  val assignmentUrl: URL = new URL(student.gitHubHome + assessmentName)

  private val rawBase: String = s"https://raw.githubusercontent.com/${student.githubUsername}/$assessmentName/master"

  /**
    * The URL for the assignment implementation
    */
  val assignmentClazzURL: URL = new URL(s"$rawBase/src/main/scala/fhj/swengb/assignments/$name/${student.userId}/${Name}Assignment.scala")

  /**
    * The URL for the assignment test
    */
  val assignmentTestClazzURL: URL = new URL(s"$rawBase/src/test/scala/fhj/swengb/assignments/$name/${student.userId}/${Name}AssignmentTest.scala")

  /**
    * fetch the main page for this github repository, assert that the page contains a certain
    * string, the project name.
    */
  lazy val gitHubRepoExists: Boolean =
    fetchFromInternet(assignmentUrl).map(s => s.contains(assessmentName)).getOrElse(false)

  lazy val remoteAssignment: Try[String] = fetchFromInternet(assignmentClazzURL)

  lazy val remoteAssignmentTest: Try[String] = fetchFromInternet(assignmentTestClazzURL)

  lazy val (srcExists, testExists) = testContent.getOrElse((false, false))


  def testContent: Try[(Boolean, Boolean)] = {
    for {a <- remoteAssignment
         at <- remoteAssignmentTest} yield
      (a.contains("package"), at.contains("package"))
  }


  /**
    * This method call fetches the source code of the student to the workspace.
    */
  def generateSources(): Unit = {

    val assignmentFile = new File(s"fhj.swengb.lecturer/students/src/main/scala/fhj/swengb/assignments/$name/${student.userId}/${Name}Assignment.scala")
    val assignmentTestFile = new File(s"fhj.swengb.lecturer/students/src/test/scala/fhj/swengb/assignments/$name/${student.userId}/${Name}AssignmentTest.scala")
    val referenceTestFile = new File(s"$assessmentName/src/test/scala/fhj/swengb/assignments/$name/rladstaetter/${Name}AssignmentTest.scala")
    val referenceTestImplString: String = prepare(referenceTestFile)

    SwengbUtil.mkParent(assignmentFile)
    SwengbUtil.mkParent(assignmentTestFile)

    for (implSrc <- remoteAssignment) {
      SwengbUtil.writeToFile(assignmentFile, implSrc)
      SwengbUtil.writeToFile(assignmentTestFile, referenceTestImplString)
      //SwengbUtil.writeToFile(targetTest, testSrc)
    }

  }

  def prepare(origFile: File): String = {
    replacePackageName(Source.fromFile(origFile).mkString)
  }

  def replacePackageName(origString: String): String = {
    origString.replaceAll("rladstaetter", student.userId)
  }

  def generateSources4Tree(): Unit = {
    generateSources()

    val packageFile = new File(s"fhj.swengb.assignments.tree/src/main/scala/fhj/swengb/assignments/tree/rladstaetter/package.scala")
    val pt2DFile = new File(s"fhj.swengb.assignments.tree/src/main/scala/fhj/swengb/assignments/tree/rladstaetter/Pt2D.scala")
    val packageTargetFile = new File(s"fhj.swengb.lecturer/students/src/main/scala/fhj/swengb/assignments/$name/${student.userId}/package.scala")
    val pt2DTargetFile = new File(s"fhj.swengb.lecturer/students/src/main/scala/fhj/swengb/assignments/$name/${student.userId}/Pt2D.scala")

    val assignmentFile = new File(s"fhj.swengb.lecturer/students/src/main/scala/fhj/swengb/assignments/$name/${student.userId}/${Name}Assignment.scala")

    if (assignmentFile.exists) {
      SwengbUtil.writeToFile(packageTargetFile, prepare(packageFile))
      SwengbUtil.writeToFile(pt2DTargetFile, prepare(pt2DFile))
    }


  }


}
