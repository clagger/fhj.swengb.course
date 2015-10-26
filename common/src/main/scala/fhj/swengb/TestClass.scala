package fhj.swengb

/**
  * Created by lad on 16.10.15.
  */
case class Example(val a : Int, val b : String) {

  val doit: String = a + b

  def thisisamethod(c : Double): Double = {
    c * 10
  }

}
