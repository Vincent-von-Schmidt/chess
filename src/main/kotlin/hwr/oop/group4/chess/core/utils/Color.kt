package hwr.oop.group4.chess.core.utils

enum class Color {
  WHITE, BLACK;
}

fun Color.opposite(): Color =
  when (this) {
    Color.WHITE -> Color.BLACK
    Color.BLACK -> Color.WHITE
  }

fun Color.toChar(): Char =
  when (this) {
    Color.WHITE -> 'w'
    Color.BLACK -> 'b'
  }
