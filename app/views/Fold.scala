package views

/**
 * The primary difference is the order in which the fold operation iterates through the collection in question.
 * foldLeft starts on the left side—the first item—and iterates to the right;
 * foldRight starts on the right side—the last item—and iterates to the left.
 * fold goes in no particular order.
 */
object Fold {
  def main(array: Array[String]): Unit = {

    val number = List(1, 2, 3, 4, 5, 6)
    println(number.foldLeft(0) {(first, second) => first + second })

  }
}
