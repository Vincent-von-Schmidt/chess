package hwr.oop

class Main {
  fun sayHello(): String {
    return "Hello World!"
  }
}

fun main() {
  println(Main().sayHello())

  var notation: String = "Kg2"

  when (notation[0]) {
    'K' -> println("Kg2")
    else -> println("Not a Kg2")
  }

}
