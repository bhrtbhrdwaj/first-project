


object Area {
  def circle(radius: Int): Double = {
    Math.PI * Math.pow(radius, 2)
  }

  def main(args: Array[String]): Unit = {
    //converting a method to function
    val function_method: Int => Double = circle
    val function_method_short_hand = circle _

    println(s"Function method ${function_method(10)}")
    println(s"Function method ${function_method_short_hand(10)}")

    val greeting = new Greeting("Bharat", "Bhardwaj")
    //DSL domain specific language
    println(new Distance(10) apart 20)
    //Invokes apply
    val v = ObjectX(10)

    val in = "6"
    val result: Either[String, Int] =
      try Right(in.toInt)
      catch {
        case e: NumberFormatException => Left(in)
      }

    result match {
      case Right(x) => s"You passed me the Int: $x, which I will increment. $x + 1 = ${x + 1}"
      case Left(x) => s"You passed me the String: $x"
    }
    println(result)
    println(ObjectX.add(10, 10))


  }
}

class Greeting(name: String) {
  println(s"Welcome ${name}")
  //auxiliary constructors
  def this(name: String, other: String) = {
    this(name)
    println(s"Two parameter constructor: ${other}")
  }
  def this() = this("there")
}

class Distance(from: Int) {
  def apart(to: Int) = from - to
}
//private constructor (constant class and can also be used to not instantiate a class)
class ObjectX private (num: Int)

//companion object
object ObjectX {
  //for defining constants
  val CONSTANT = 10
  val CONSTANT_ONE = "20"

  //static methods
  def add(num: Int, valueToAdd: Int) = num + valueToAdd

  def apply(num: Int): ObjectX = {
    new ObjectX(num)
  }

}


