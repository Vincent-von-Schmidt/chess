package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.utils.Color

class Turn(fen: String) {
  var colorToMove = ReaderFEN(fen).activeColor

  fun switchTurn() {
    colorToMove = if (colorToMove == Color.WHITE) {
      Color.BLACK
    } else {
      Color.WHITE
    }
  }
}