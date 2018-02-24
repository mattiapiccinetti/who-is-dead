package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.mvc.Result
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.Future


class IsDeadControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  def assertThat(
    result: Future[Result],
    status: Int,
    contentType: Option[String],
    includedContent: String): Unit = {
      Helpers.status(result) mustBe status
      Helpers.contentType(result) mustBe contentType
      Helpers.contentAsString(result) must include(includedContent)
  }

  "IsDeadController GET / for text/html content type" should {

    val fakeRequest = FakeRequest(GET, "/").withHeaders("Accept" -> "text/html")

    "render the index page from a new instance of controller" in {
      assertThat(
        result = new IsDeadController(stubControllerComponents()).index().apply(fakeRequest),
        status = OK,
        contentType = Some("text/html"),
        includedContent = "___ is dead")
    }

    "render the index page from the application" in {
      assertThat(
        result = inject[IsDeadController].index().apply(fakeRequest),
        status = OK,
        contentType = Some("text/html"),
        includedContent = "___ is dead")
    }

    "render the index page from the router" in {
      assertThat(
        result = route(app, fakeRequest).get,
        status = OK,
        contentType = Some("text/html"),
        includedContent = "___ is dead")
    }
  }

  "IsDeadController GET /foo for text/html content type" should {

    val fakeRequest = FakeRequest(GET, "/foo").withHeaders("Accept" -> "text/html")

    "render the index page from a new instance of controller" in {
      assertThat(
        result = new IsDeadController(stubControllerComponents()).isDead("foo").apply(fakeRequest),
        status = OK,
        contentType = Some("text/html"),
        includedContent = "foo is dead")

    }

    "render the index page from the application" in {
      assertThat(
        result = inject[IsDeadController].isDead("foo").apply(fakeRequest),
        status = OK,
        contentType = Some("text/html"),
        includedContent = "foo is dead")
    }

    "render the index page from the router" in {
      assertThat(
        result = route(app, fakeRequest).get,
        status = OK,
        contentType = Some("text/html"),
        includedContent = "foo is dead")
    }
  }

  "IsDeadController GET /foo for application/json content type" should {

    val fakeRequest = FakeRequest(GET, "/foo").withHeaders("Accept" -> "application/json")

    "render the index page from a new instance of controller" in {
      assertThat(
        result = new IsDeadController(stubControllerComponents()).isDead("foo").apply(fakeRequest),
        status = OK,
        contentType = Some("application/json"),
        includedContent = "foo is dead")
    }

    "render the index page from the application" in {
      assertThat(
        result = inject[IsDeadController].isDead("foo").apply(fakeRequest),
        status = OK,
        contentType = Some("application/json"),
        includedContent = "foo is dead")
    }

    "render the index page from the router" in {
      assertThat(
        result = route(app, fakeRequest).get,
        status = OK,
        contentType = Some("application/json"),
        includedContent = "foo is dead")
    }
  }
}
