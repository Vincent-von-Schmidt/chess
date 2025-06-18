package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Field

enum class KnightJump {
  TOP_LEFT_LEFT,
  TOP_TOP_LEFT,
  TOP_TOP_RIGHT,
  TOP_RIGHT_RIGHT,
  BOTTOM_RIGHT_RIGHT,
  BOTTOM_BOTTOM_RIGHT,
  BOTTOM_BOTTOM_LEFT,
  BOTTOM_LEFT_LEFT;

  fun moveFrom(field: Field): Field? = when (this) {
    TOP_LEFT_LEFT -> field.top?.left?.left
    TOP_TOP_LEFT -> field.top?.top?.left
    TOP_TOP_RIGHT -> field.top?.top?.right
    TOP_RIGHT_RIGHT -> field.top?.right?.right
    BOTTOM_RIGHT_RIGHT -> field.bottom?.right?.right
    BOTTOM_BOTTOM_RIGHT -> field.bottom?.bottom?.right
    BOTTOM_BOTTOM_LEFT -> field.bottom?.bottom?.left
    BOTTOM_LEFT_LEFT -> field.bottom?.left?.left
  }
}
