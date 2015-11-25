package fhj.swengb

import java.net.URL

import spray.json
import spray.json._

/**
  * Created by lad on 13.11.15.
  */
object GitHub {

  case class User(login: String, avatarUrl: URL, html: URL, foll: BigDecimal, fing: BigDecimal, foll_url: URL, fing_url: URL, create: String)

  object GithubUserProtocol extends DefaultJsonProtocol {

    implicit object GithubUserJsonFormat extends RootJsonFormat[User] {
      def write(user: User): JsValue =
        JsArray(
          JsString(user.login),
          JsString(user.avatarUrl.toString),
          JsString(user.html.toString),
          JsNumber(user.foll),
          JsNumber(user.fing),
          JsString(user.foll_url.toString),
          JsString(user.fing_url.toString),
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
            val JsString(foll_url) = m("followers_url")
            val JsString(fing_url) = m("following_url")
            val JsString(create) = m("created_at")
            User(login, new URL(a_url), new URL(html), foll, fing, new URL(foll_url), new URL(fing_url), create)
          case x =>
            deserializationError("GitHubUser expected.")
        }
      }

    }

  }

}
