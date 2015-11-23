package fhj.swengb.labs.lab04

import fhj.swengb.GitHub
import org.junit.Assert._
import org.junit.Test
import spray.json._


/**
  * Shows a json parsing scenario using a json library called 'spray'.
  *
  * Created by lad on 12.11.15.
  */
class AvatarixTest {

  import GitHub.GithubUserProtocol._
  import GitHub._

  val testJson =
    """
      |{
      |  "login": "rladstaetter",
      |  "id": 119250,
      |  "avatar_url": "https://avatars.githubusercontent.com/u/119250?v=3",
      |  "gravatar_id": "",
      |  "url": "https://api.github.com/users/rladstaetter",
      |  "html_url": "https://github.com/rladstaetter",
      |  "followers_url": "https://api.github.com/users/rladstaetter/followers",
      |  "following_url": "https://api.github.com/users/rladstaetter/following{/other_user}",
      |  "gists_url": "https://api.github.com/users/rladstaetter/gists{/gist_id}",
      |  "starred_url": "https://api.github.com/users/rladstaetter/starred{/owner}{/repo}",
      |  "subscriptions_url": "https://api.github.com/users/rladstaetter/subscriptions",
      |  "organizations_url": "https://api.github.com/users/rladstaetter/orgs",
      |  "repos_url": "https://api.github.com/users/rladstaetter/repos",
      |  "events_url": "https://api.github.com/users/rladstaetter/events{/privacy}",
      |  "received_events_url": "https://api.github.com/users/rladstaetter/received_events",
      |  "type": "User",
      |  "site_admin": false,
      |  "name": "Robert Ladstätter",
      |  "company": null,
      |  "blog": "http://ladstatt.blogspot.com/",
      |  "location": null,
      |  "email": null,
      |  "hireable": null,
      |  "bio": null,
      |  "public_repos": 29,
      |  "public_gists": 25,
      |  "followers": 39,
      |  "following": 10,
      |  "created_at": "2009-08-25T11:31:55Z",
      |  "updated_at": "2015-11-08T16:38:05Z"
      |}
    """.
      stripMargin


  @Test def test(): Unit = {
    val parseJson: JsValue = testJson.parseJson
    val u: User = parseJson.convertTo[User]
    assertEquals("rladstaetter", u.login)
  }

}
