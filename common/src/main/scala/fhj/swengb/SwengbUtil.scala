package fhj.swengb

import java.io.{IOException, File}
import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file._

import scala.io.Source

/**
  * Useful tooling for basic operations
  */
object SwengbUtil {

  def mkParent(file : File) : Unit = {
    if (!file.getParentFile.exists()) {
      file.getParentFile.mkdirs()
    }
  }
  def writeToFile(file: File, content: String): File = {
    Files.write(Paths.get(file.toURI), content.getBytes(StandardCharsets.UTF_8)).toFile
  }

  def fetch(url: URL): String = {
    Source.fromURL(url).mkString
  }

  /**
    * function to measure execution time of first function, optionally executing a display function,
    * returning the time in milliseconds
    */
  def time[A](a: => A, display: Long => Unit = s => ()): A = {
    val now = System.nanoTime
    val result = a
    val micros = (System.nanoTime - now) / 1000000
    display(micros)
    result
  }

  def removeRecursive(path: Path): Unit = {
    Files.walkFileTree(path, new SimpleFileVisitor[Path]() {
      override def visitFile(path: Path, attrs: BasicFileAttributes): FileVisitResult = {
        Files.delete(path)
        FileVisitResult.CONTINUE
      }

      override def visitFileFailed(path: Path, exc: IOException): FileVisitResult = {
        Files.delete(path)
        FileVisitResult.CONTINUE;
      }

      override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
        if (exc == null) {
          Files.delete(dir)
          FileVisitResult.CONTINUE
        } else {
          throw exc
        }
      }
    }
    )
  }
}


