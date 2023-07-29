package controllers

import com.google.inject.Inject
import model.slick.EmployeeDTO
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}
import repository.slick.EmployeeRepo

import scala.concurrent.ExecutionContext

/**
 * Slick is not an ORM like Hibernate. Slick is more like a functional-relational mapper
 */
class EmployeeController @Inject()(val controllerComponents: ControllerComponents,
                                   employeeService: EmployeeRepo)(implicit executionContext: ExecutionContext) extends BaseController {
  private val logger:Logger = Logger(this.getClass())
  def getEmployees()  = Action.async {
        implicit request =>
          val x = employeeService.findAll()
            .map(_.map(EmployeeDTO.transform(_)))
          //val employeeDTO = EmployeeDTO(firstName = Some("Bharat"), employeeId = 10)
     /* val employeeResponseList = for {
        employees   <- employeeService.findAll()
      } yield employees
     logger.info(s"Employees in DB are ${employeeResponseList}")
     */
      x.map(Json.toJson(_)).map(Ok(_))
  }

  /*employeeService.findAll().onComplete {
     case Success(posts) => println(posts)
     case Failure(t)
     => println("An error has occurred: " + t.getMessage)
   }*/
  //println("na" * 16 + "BATMAN!!!")

}
