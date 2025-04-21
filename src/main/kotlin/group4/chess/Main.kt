package group4.chess

import group4.chess.fen.FenReader

fun main() {
  val fen: FenReader = FenReader("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2")

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
