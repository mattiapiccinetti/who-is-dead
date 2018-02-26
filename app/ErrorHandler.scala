import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent._
import javax.inject.Singleton

import play.api.Logger

@Singleton
class ErrorHandler extends HttpErrorHandler {

  val logger: Logger = Logger(this.getClass)

  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    val message = s"[$statusCode] you're dead"
    logger.info(s"$message, path: ${request.path}")

    Future.successful(Status(statusCode)(views.html.index(message)))
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    val message = s"[500] I'm dead"
    logger.error(s"$message, request: $request, exception")

    Future.successful(InternalServerError(views.html.index("[500] I'm dead")))
  }
}