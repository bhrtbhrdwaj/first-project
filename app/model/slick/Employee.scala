package model.slick
import play.api.libs.json.{Json}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

import java.sql.Date

case class Employee(employeeId: Int, firstName: String, lastName: String,
                    salary: Double, joiningDate: Date, department: String,
                    isDeleted: Boolean, createdAt: Date, updatedAt: Date)

object Employee {
  implicit val format = Json.format[Employee]
  lazy val tupled = (Employee.apply _).tupled
}

//convert our models to slick objects, i.e.
// provide information for slick to map between classes and tables.

// this first "employee" string refers to my database table name
class EmployeeTableDef(tag: Tag) extends Table[Employee](tag, "employee") {

  def employeeId = column[Int]("employee_id", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def salary = column[Double]("salary")
  def joiningDate = column[Date]("joining_date")
  def department = column[String]("department")
  def isDeleted = column[Boolean]("is_deleted")
  def createdAt = column[Date]("created_at")
  def updatedAt = column[Date]("updated_at")


  override def * = (employeeId, firstName, lastName, salary,
    joiningDate, department, isDeleted, createdAt, updatedAt) <> (Employee.tupled, Employee.unapply)

}
