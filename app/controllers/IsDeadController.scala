package controllers

import javax.inject._

import play.api.mvc._

@Singleton
class IsDeadController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index(): Action[AnyContent] = isDead("___")

  def isDead(text: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(text))
  }
}
