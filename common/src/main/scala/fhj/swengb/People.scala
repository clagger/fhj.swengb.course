package fhj.swengb

import java.time.LocalDate


object AttendanceList {

}

object Lectures {

  case class Lecture(date: LocalDate, missingStudents: Set[Student] = Set())

  val oct13 = Lecture(LocalDate.of(2015, 10, 13))
  val oct27 = Lecture(LocalDate.of(2015, 10, 27))
  val nov3 = Lecture(LocalDate.of(2015, 11, 3))
  val nov10 = Lecture(LocalDate.of(2015, 11, 10))
  val nov24 = Lecture(LocalDate.of(2015, 11, 24))
  val dec1 = Lecture(LocalDate.of(2015, 12, 1))
  val dec15 = Lecture(LocalDate.of(2015, 12, 15))
  val jan1 = Lecture(LocalDate.of(2016, 1, 12))

  val lectures = Set(oct13, oct27, nov3, nov10, nov24, dec1, dec15, jan1)

}

object People {

  val rladstaetter =
    Speaker("Robert", "Ladstätter", "rladstaetter", 0)

  val speakers =
    Set(
      rladstaetter,
      Speaker("Johann", "Blauensteiner", "johblau", 0),
      Speaker("Peter", "Salhofer", "AnotherCodeArtist", 0))

  val exampleStudent = Student("John", "Doe", "rladstaetter", 4)

  def mkStudents(): Set[Student] = {
    val src = getClass.getResourceAsStream("/fhj/swengb/students.csv")
    assert(src != null, "Could not find student definition file")
    (for (l <- io.Source.fromInputStream(src).getLines()) yield {
      val Array(group, secondName, firstName) = l.split(",")
      Student(firstName, secondName, "", group.toInt)
    }).toSet
  }

  val students: Set[Student] = mkStudents() ++ Set(exampleStudent)

}





