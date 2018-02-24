package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class IsDeadController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index(): Action[AnyContent] = isDead("___")

  def isDead(text: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    render {
      case Accepts.Json() => Ok(Json.obj("text" -> s"$text is dead"))
      case Accepts.Html() => Ok(views.html.index(text))
    }
  }
}
