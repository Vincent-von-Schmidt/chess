package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.utils.Color

class GameOverException(
  drawReason: DrawReason?,
  endReason: GameState,
  winner: Color?,
) : Exception(
  when {
    drawReason == null && winner != null -> "The game ended in $endReason. The winner is $winner"
    drawReason == null && endReason == GameState.DRAW && winner == null -> "The game ended in $endReason, because of $drawReason"
    else -> "The game ended in a $endReason"
  }
)