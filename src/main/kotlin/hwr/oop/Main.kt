package hwr.oop

class KotlinExample {
  fun sayHello(): String {
    return "Hello World!"
  }
}

fun main() {
  println(KotlinExample().sayHello())

  var a = Regex("aabb")
  var b: String = "aabbc"
  println(a.containsMatchIn(b))

}
