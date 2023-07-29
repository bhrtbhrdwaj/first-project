package action

import com.google.inject.Inject
import play.api.Logging
import play.api.mvc.{Action, ActionBuilderImpl, BodyParsers, Request, Result}

import scala.concurrent.{ExecutionContext, Future}

class ApplicationAction @Inject() (parser: BodyParsers.Default)(implicit executionContext: ExecutionContext) extends ActionBuilderImpl(parser) with Logging {
  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]) = {
    block(request)
  }
  //Actions can be mixed in to action builders using the composeAction method
  override def composeAction[A](action: Action[A]) = new WrappingAction[A](action)
}

