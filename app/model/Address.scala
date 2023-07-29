package model

import play.api.libs.json.{Json}

case class Address(city: String, pincode: Long)
object Address {
    implicit val format = Json.format[Address]
}
