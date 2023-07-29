package repository.slick

import com.google.inject.Inject
import model.slick.{Employee, EmployeeTableDef}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.Future

class EmployeeRepo @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  val employees = TableQuery[EmployeeTableDef]

  def findAll(): Future[Seq[Employee]] = {
    dbConfig.db.run(employees.result)
    }

  /*def add(user: User): Future[String] = {
    dbConfig.db.run(users += user).map(res => "User successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(users.filter(_.id === id).delete)
  }

  def get(id: Long): Future[Option[User]] = {
    dbConfig.db.run(users.filter(_.id === id).result.headOption)
  }*/
}
