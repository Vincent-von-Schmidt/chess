package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Field

enum class Direction {
  TOP, BOTTOM, RIGHT, LEFT,
  TOP_RIGHT, TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT;

  fun moveFrom(field: Field): Field? = when (this) {
    TOP -> field.top
    BOTTOM -> field.bottom
    LEFT -> field.left
    RIGHT -> field.right
    TOP_LEFT -> field.top?.left
    TOP_RIGHT -> field.top?.right
    BOTTOM_LEFT -> field.bottom?.left
    BOTTOM_RIGHT -> field.bottom?.right
  }
}