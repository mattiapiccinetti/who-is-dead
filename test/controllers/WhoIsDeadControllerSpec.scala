package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.mvc.Result
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.Future


class WhoIsDeadControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  def assertThat(result: Future[Result])
                (status: Int, contentType: Option[String], includedContent: String): Unit = {

    Helpers.status(result) mustBe status
    Helpers.contentType(result) mustBe contentType
    Helpers.contentAsString(result) must include(includedContent)
  }

  "WhoIsDeadController GET / for text/html content type" should {

    val fakeRequest = FakeRequest(GET, "/").withHeaders("Accept" -> "text/html")

    "render the index page from a new instance of controller" in {
      val actual = new WhoIsDeadController(stubControllerComponents()).index().apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("text/html"),
        includedContent = "who is dead?")
    }

    "render the index page from the application" in {
      val actual = inject[WhoIsDeadController].index().apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("text/html"),
        includedContent = "who is dead?")
    }

    "render the index page from the router" in {
      val actual = route(app, fakeRequest).get

      assertThat(actual)(
        status = OK,
        contentType = Some("text/html"),
        includedContent = "who is dead?")
    }
  }

  "WhoIsDeadController GET / for application/json content type" should {

    val fakeRequest = FakeRequest(GET, "/").withHeaders("Accept" -> "application/json")

    "render the index page from a new instance of controller" in {
      val actual = new WhoIsDeadController(stubControllerComponents()).index().apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("application/json"),
        includedContent = "who is dead?")
    }

    "render the index page from the application" in {
      val actual = inject[WhoIsDeadController].index().apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("application/json"),
        includedContent = "who is dead?")
    }

    "render the index page from the router" in {
      val actual = route(app, fakeRequest).get

      assertThat(actual)(
        status = OK,
        contentType = Some("application/json"),
        includedContent = "who is dead?")
    }
  }

  "WhoIsDeadController GET /foo for text/html content type" should {

    val fakeRequest = FakeRequest(GET, "/foo").withHeaders("Accept" -> "text/html")

    "render the index page from a new instance of controller" in {
      val actual = new WhoIsDeadController(stubControllerComponents()).isDead("foo").apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("text/html"),
        includedContent = "foo is dead")
    }

    "render the index page from the application" in {
      val actual = inject[WhoIsDeadController].isDead("foo").apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("text/html"),
        includedContent = "foo is dead")
    }

    "render the index page from the router" in {
      val actual = route(app, fakeRequest).get

      assertThat(actual)(
        status = OK,
        contentType = Some("text/html"),
        includedContent = "foo is dead")
    }
  }

  "WhoIsDeadController GET /foo for application/json content type" should {

    val fakeRequest = FakeRequest(GET, "/foo").withHeaders("Accept" -> "application/json")

    "render the index page from a new instance of controller" in {
      val actual = new WhoIsDeadController(stubControllerComponents()).isDead("foo").apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("application/json"),
        includedContent = "foo is dead")
    }

    "render the index page from the application" in {
      val actual = inject[WhoIsDeadController].isDead("foo").apply(fakeRequest)

      assertThat(actual)(
        status = OK,
        contentType = Some("application/json"),
        includedContent = "foo is dead")
    }

    "render the index page from the router" in {
      val actual = route(app, fakeRequest).get

      assertThat(actual)(
        status = OK,
        contentType = Some("application/json"),
        includedContent = "foo is dead")
    }
  }
}

