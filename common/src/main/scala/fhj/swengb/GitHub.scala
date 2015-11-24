package fhj.swengb

import java.net.URL

import spray.json
import spray.json._

/**
  * Created by lad on 13.11.15.
  */
object GitHub {

  case class User(login: String, avatarUrl: URL, html: URL, foll: BigDecimal, fing: BigDecimal, create: String)

  object GithubUserProtocol extends DefaultJsonProtocol {

    implicit object GithubUserJsonFormat extends RootJsonFormat[User] {
      def write(user: User): JsValue =
        JsArray(
          JsString(user.login),
          JsString(user.avatarUrl.toString),
          JsString(user.html.toString),
          JsNumber(user.foll),
          JsNumber(user.fing),
          JsString(user.create)
        )


      def read(value: JsValue): User = {
        value match {
          case JsObject(m) =>
            val JsString(login) = m("login")
            val JsString(a_url) = m("avatar_url")
            val JsString(html) = m("html_url")
            val JsNumber(foll) = m("followers")
            val JsNumber(fing) = m("following")
            val JsString(create) = m("created_at")
            User(login, new URL(a_url), new URL(html), foll, fing, create)
          case x =>
            deserializationError("GitHubUser expected.")
        }
      }

    }

  }

}
