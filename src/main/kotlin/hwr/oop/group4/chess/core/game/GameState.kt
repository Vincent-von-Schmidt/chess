package hwr.oop.group4.chess.core.game

enum class GameState {
  NORMAL, CHECK, CHECKMATE, DRAW;

  override fun toString(): String = when (this) {
    NORMAL -> "NORMAL"
    CHECK -> "CHECK"
    CHECKMATE -> "CHECKMATE"
    DRAW -> "DRAW"
  }
}