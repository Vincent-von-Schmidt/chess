package hwr.oop.group4.chess.core.game

class DrawException(
  state: GameState,
  drawReason: DrawReason,
) : Exception(
  "The game ended in a ${state}, due to $drawReason"
)