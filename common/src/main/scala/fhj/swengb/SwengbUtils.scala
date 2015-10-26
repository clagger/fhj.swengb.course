package fhj.swengb

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.{Paths, Files}

/**
  * Created by lad on 26.10.15.
  */
object SwengbUtils {

  def writeToFile(file: File, content: String): File = {
    Files.write(Paths.get(file.toURI), content.getBytes(StandardCharsets.UTF_8)).toFile
  }

}
