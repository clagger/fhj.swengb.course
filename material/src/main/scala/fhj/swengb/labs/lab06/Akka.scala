package fhj.swengb.labs.lab06

import akka.actor.{Props, Actor}

object Greeter {
  case object Greet
  case object Done
}


object Main {

  def main(args: Array[String]): Unit = {
    akka.Main.main(Array(classOf[HelloWorld].getName))
  }

}

class HelloWorld extends Actor {

  override def preStart(): Unit = {
    // create the greeter actor
    val greeter = context.actorOf(Props[Greeter], "greeter")
    // tell it to perform the greeting
    greeter ! Greeter.Greet
  }

  def receive = {
    // when the greeter is done, stop this actor and with it the application
    case Greeter.Done => context.stop(self)
  }
}


class Greeter extends Actor {
  def receive = {
    case Greeter.Greet =>
      println("Hello World!")
      sender() ! Greeter.Done
  }
}