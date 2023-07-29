package model.slick

import play.api.libs.json.{Format, Json}
import java.sql.Date

case class EmployeeDTO(employeeId: Option[Int] = None, firstName: Option[String] = None, lastName: Option[String] = None,
                       salary: Option[Double] = None, joiningDate: Option[Date] = None, department: Option[String] = None,
                       isDeleted: Option[Boolean] = None, createdAt: Option[Date] = None)
object EmployeeDTO {
  def transform(employee: Employee) = {
    EmployeeDTO(Some(employee.employeeId), Some(employee.firstName), Some(employee.lastName),
     Some(employee.salary),
      Some(employee.joiningDate), Some(employee.department), Some(employee.isDeleted))
  }
  implicit val format: Format[EmployeeDTO] = Json.format[EmployeeDTO]
}
