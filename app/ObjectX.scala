object HigherOrderFunction {

  def finalBillCalculator(serviceCharge: Int, vat: Int, netBill: Int) = {
    netBill + (serviceCharge * netBill) / 100 + (vat * netBill) / 100
  }
  //curring function - last parameter price is the actual parameter
  def finalPrice(vat: Int)(serviceCharge: Int)(price: Int) = {
    vat + serviceCharge + price
  }
  def main(args: Array[String]) = {
    //partial applied function (will pass the other arguments latter)
    val myBillCalculator = finalBillCalculator(serviceCharge = 10, _: Int, _: Int)
    println(s"Final Bill: ${myBillCalculator(10, 10)}")

    //curring function (Transferring function into chain of calls)
    val one = finalPrice(10)_
    val two = one(10)
    val finalCall = two(20)
    println(s"Final call result: ${finalCall}")

    //existing function to curried function
    val curryRetention = (finalBillCalculator _).curried
    val one1 = curryRetention(10)
    val two2 = one1(10)
    val finalCallF = two2(20)
    println(s"Existing function to curry: ${finalCallF}")

    val serviceCharge = 10 //capturing/ binding of free variable by closing the function
    //closure - function value created at runtime from bill is called a closure
    val bill = (netBill: Int) => netBill * (1 + serviceCharge / 100) // serviceCharge needs to be defined first
    println(s"Bill: ${bill(10)}")
    //#1
    val cafe = new Cafe(10)
    println(s"Bill: ${cafe.finalBill(10)}")

  }
}

//#1
class Cafe(val serviceCharge: Int) {
  val finalBill = (netBill: Int) => netBill * (1 + serviceCharge / 100)
}