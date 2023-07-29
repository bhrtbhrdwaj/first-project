package views

/**
 * Higher order function is a function that either takes a function as argument or returns a function
 * Arity - 0
 * Scala allows the omission of parentheses on methods of arity-0 (no arguments): reply() same as reply
 * ----------------------------
 * Arity - 1
 * Scala has a special punctuation-free syntax for invoking methods of arity-1 (one argument).
 * This should generally be avoided, but with the following exceptions for operators and higher-order functions. In these cases it should only be used for purely-functional methods (methods with no side-effects).
 *  recommended
 *  names.mkString(",")
 *  also sometimes seen; controversial
 *  names mkString ","
 *  wrong - has side-effects
 *  javaList add item
 */
object ErrorHandling {
  lazy val divide = (numerator: Int, denominator: Int) => {
    if(denominator == 0)
      Left("Can't divide by 0")
    else
      Right(numerator/denominator)
  }
  def main(array: Array[String]): Unit = {
    divide(10, 0) match {
      case Left(value) => println(value)
      case Right(value) => print(value)
    }

    val x = try {
      val x = 10/ 0
    }
    catch {
      case foo: NullPointerException => foo.getMessage
      case any: Throwable => any.getMessage
    }
    println(x)
  }
}
