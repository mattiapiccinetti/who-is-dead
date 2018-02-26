package controllers

import javax.inject._

import play.api.Logger
import play.api.http.MediaRange
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class WhoIsDeadController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val logger: Logger = Logger(this.getClass)

  def index(): Action[AnyContent] = Action { implicit request =>
    render { partialHandler("who is dead?") }
  }

  def isDead(text: String): Action[AnyContent] = Action { implicit request =>
    render { partialHandler(s"$text is dead") }
  }

  def partialHandler(message: String): PartialFunction[MediaRange, Result] = {

    case Accepts.Json() =>
      logger.debug(s"$message as json")
      Ok(Json.obj("text" -> message))

    case Accepts.Html() =>
      logger.debug(s"$message as html")
      Ok(views.html.index(message))
  }
}
