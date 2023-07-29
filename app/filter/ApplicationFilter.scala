package filter

import akka.stream.Materializer
import com.google.inject.Inject
import play.api.Logging
import play.api.mvc.{Filter, RequestHeader, Result}

import scala.concurrent.{ExecutionContext, Future}

class ApplicationFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter with Logging {
def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader) = {
  val start = System.currentTimeMillis()
  nextFilter(requestHeader).map {result =>
    val end = System.currentTimeMillis()
    val requestTime = end - start

    logger.info(s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}")
    result.withHeaders("Request-Time"-> requestTime.toString)
  }
}
}
