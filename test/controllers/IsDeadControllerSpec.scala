package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._


class IsDeadControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "IsDeadController GET /" should {

    "render the index page from a new instance of controller" in {
      val controller = new IsDeadController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("___ is dead")
    }

    "render the index page from the application" in {
      val controller = inject[IsDeadController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("___ is dead")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("___ is dead")
    }
  }

  "IsDeadController GET /foo" should {

    "render the index page from a new instance of controller" in {
      val controller = new IsDeadController(stubControllerComponents())
      val home = controller.isDead("foo").apply(FakeRequest(GET, "/foo"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("foo is dead")
    }

    "render the index page from the application" in {
      val controller = inject[IsDeadController]
      val home = controller.isDead("foo").apply(FakeRequest(GET, "/foo"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("foo is dead")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/foo")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("foo is dead")
    }
  }
}
