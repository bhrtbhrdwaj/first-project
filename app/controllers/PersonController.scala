package controllers

import action.{ApplicationAction, WrappingAction}
import com.google.inject.Inject
import play.api.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, BaseController, ControllerComponents}
import service.PersonService

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

class PersonController @Inject()(val controllerComponents: ControllerComponents,
                                 personService: PersonService,
                                 val applicationAction: ApplicationAction)(implicit executionContext: ExecutionContext) extends BaseController{
  private val logger = Logger(this.getClass)
  def ping() = Action {
    implicit request =>
    Ok("pong")
  }

  def getById(itemId: Long) = Action {
    implicit request =>
    Ok("")
  }
  def getAllPerson(id: Int) = applicationAction {
    implicit request =>
      val persons = personService.findAllPerson()
      persons match {
        case Nil => NoContent
        case response => Ok(Json.toJson(response))
      }
  }
  //Action(parse.json).async
  def getFromExternalAPIX() = Action.async {
    implicit request =>
      val response = personService.getFromExternalAPI("https://jsonplaceholder.typicode.com/todos/1")
      response.map(Ok(_))
  }

  def getFromExternalAPI() = Action.async {
    implicit request =>
      try {
        //val result = riskyCalculation

        // Log result if successful
        //logger.debug(s"Result=$result")
      } catch {
        case t: Throwable => {
          // Log error with message and Throwable.
          //logger.error("Exception with riskyCalculation", t)
        }
      }
      val response = personService.getFromExternalAPI("https://jsonplaceholder.typicode.com/todo/1")
      response.onComplete({
        case Success(value) => //do something with value
        case Failure(exception) => //do something with exception
      })
      response.map(Ok(_))
  }
}
