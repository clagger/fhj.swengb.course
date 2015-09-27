package fhj.swengb

/**
 * Created by lad on 24.09.15.
 */
trait CanLog {

  def logInfo(msg : String) = System.out.println(msg)
  def logError(msg : String) = System.err.println(msg)

}
