import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent._
import javax.inject.Singleton

@Singleton
class ErrorHandler extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(Status(statusCode)(views.html.index(statusCode.toString)))
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(InternalServerError(views.html.index("500")))
  }
}