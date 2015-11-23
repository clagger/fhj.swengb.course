package fhj.swengb

import java.net.URL

import spray.json._

/**
  * Created by lad on 13.11.15.
  */
object GitHub {

  case class User(login: String, avatarUrl: URL)

  object GithubUserProtocol extends DefaultJsonProtocol {

    implicit object GithubUserJsonFormat extends RootJsonFormat[User] {
      def write(user: User): JsValue =
        JsArray(
          JsString(user.login),
          JsString(user.avatarUrl.toString)
        )


      def read(value: JsValue): User = {
        value match {
          case JsObject(m) =>
            val JsString(login) = m("login")
            val JsString(a_url) = m("avatar_url")
            User(login, new URL(a_url))
          case x =>
            deserializationError("GitHubUser expected.")
        }
      }

    }

  }

}
