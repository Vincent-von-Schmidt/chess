package hwr.oop

enum class Color {
  WHITE, BLACK
}

data class Fen (val notation: String) {
  val piecePlacement: List<String> = notation.split(" ").elementAt(0).split("/")
  val activeColor: Color = when (notation.split(" ").elementAt(1)) {
    "w" -> Color.WHITE
    "b" -> Color.BLACK
    else -> Color.WHITE // if not given asume white starts
  }
  val castle: String = notation.split(" ").elementAt(2)
  val enpassant: String = notation.split(" ").elementAt(3)
  val halfmoves: Int = notation.split(" ").elementAt(4).toInt()
  val fullmoves: Int = notation.split(" ").elementAt(5).toInt()
}

fun main() {
  val fen: Fen = Fen("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2")

  println(fen.piecePlacement)
  println(fen.activeColor)
  println(fen.castle)
  println(fen.enpassant)
  println(fen.halfmoves)
  println(fen.fullmoves)

  for (line in fen.piecePlacement) {
    var lineText: String = ""

    for (field in line) {
      if (field in '0'..'9') {
        lineText += "".padStart(field.digitToInt() * 2)
      } else {
        lineText += "$field "
      }
    }
    println(lineText)
  }
}
