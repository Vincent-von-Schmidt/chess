package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.utils.Color

class Turn(fen: FEN) {
  var colorToMove = fen.activeColor

  fun switchTurn() {
    colorToMove = if (colorToMove == Color.WHITE) {
      Color.BLACK
    } else {
      Color.WHITE
    }
  }
}