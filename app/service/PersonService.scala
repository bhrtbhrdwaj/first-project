package service

import com.google.inject.ImplementedBy
import model.Person
import play.api.libs.json.JsValue
import service.implementation.PersonServiceImpl

import scala.concurrent.Future

@ImplementedBy(classOf[PersonServiceImpl])
trait PersonService {
  def findAllPerson() : Seq[Person]
  def getFromExternalAPI(url: String): Future[JsValue]

}
