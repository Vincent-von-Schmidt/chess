package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.utils.Color

class CheckException(colorInCheck: Color) : Exception(
  "Move would leave $colorInCheck King in check"
)