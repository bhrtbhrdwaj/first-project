package model

import play.api.libs.json.{Json}

case class Person(name: String, age: Int, salary: Double)
object Person {
    implicit val format = Json.format[Person]
    // Generates Writes and Reads for Person and Address thanks to Json Macros
    //    implicit val addressFormat = Json.format[Address]
}
