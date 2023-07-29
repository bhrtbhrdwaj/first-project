package action


import play.api.Logging
import play.api.mvc.{Action, Request, Result}
import scala.concurrent.{Future}

/**
 * Reusable action code can be implemented by wrapping actions
 * @param action
 * @tparam A
 */
case class WrappingAction[A](action: Action[A]) extends Action[A] with Logging{
  def apply(request: Request[A]): Future[Result] = {
    logger.info(s"Wrapping Action Logging request ${request.uri}")
    action(request)
  }
  override def parser           = action.parser
  override def executionContext = action.executionContext
}