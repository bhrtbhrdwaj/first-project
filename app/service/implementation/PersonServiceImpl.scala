package service.implementation

import com.google.inject.Inject
import model.Person
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.play.json.collection.JSONCollection
import service.PersonService
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

class PersonServiceImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi, wsClient: WSClient)(implicit executionContext: ExecutionContext) extends PersonService {
  /**
    * Note that the `collection` is not a `val`, but a `def`. We do not store
    * the collection reference to avoid potential problems in development with
    * Play hot - reloading.
    */
  private def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("people"))

  def findAllPersonX(): Seq[Person] = {
    val cursor = collection.map {
        _.find(Json.obj())
          .cursor[Person]()
    }
    val personsFuture = cursor.flatMap {
      _.collect[Seq](-1, err = Cursor.FailOnError[Seq[Person]]())
  }
    Await.result(personsFuture, Duration(10, TimeUnit.SECONDS))
    var persons = Seq.empty[Person]
    personsFuture.foreach(personEle => persons = personEle)
    persons
  }

  @Override
  def findAllPerson(): Seq[Person] = {
    val personsJson = Json.parse(
      """[
         { "name" : "Tom", "age" : 28, "salary" : 35000, "address" : { "city" : "Aligarh", "pincode" : 202127 } }
      ,{ "name" : "John", "age" : 25, "salary" : 35000, "address" : { "city" : "Aligarh", "pincode" : 202127 } }
      ,{ "name" : "Kathy", "age" : 23, "salary" : 35000, "address" : { "city" : "Aligarh", "pincode" : 202127 } }
        ]""".stripMargin)
    personsJson.as[Seq[Person]]
  }

  def getFromExternalAPI(url: String): Future[JsValue] = {
    wsClient.url(url).get().map(response => response.json)
  }
}

/**
 * list.map(i => i + 1)
//      ^----------^
//    function literal passed as argument

list.map(_ + 1)
//       ^---^
// Underscore shorthand for above

list.map({ i => i + 1 })
// Identical to above.
// The block only contains one expression, so it has the value of that expression
// Otherwise stated: { expr } === expr

list.map({ println("Hi"); _ + 1 })
//         ^-----2-----^  ^-3-^
//       ^------------1---------^
// 1: The argument to map is the value of this block
// 2: The first statement of the block prints something. This is only executed once,
//   because it's not the *block* being passed as argument, it's its value.
// 3: Function literal in underscore notation. This is the value of the block
//   and this is what map sees.
// Order of operations (approx. bytecode):
// load list onto stack
// load string "Hi" onto stack
// call println and pop string off stack
// create function (i => i + 1) on top of stack
// invoke map with argument (i => i + 1), popping list and function off stack

list.map { println("Hi"); _ + 1 }
// Identical to above, but Scala lets you omit the () because you are using {}

list.map({ i => println("Hi"); i + 1 })
// Function literals grow as big as they can be.
// The block contains only one expression, which is (i => println("Hi"); i + 1)
// This function prints "Hi" and then returns i + 1
// This call to map will print "Hi" for every element

list.map { i => println("Hi"); i + 1 }
// Identical to above, but Scala lets you omit the () because you are using {}
 */
