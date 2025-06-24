package hwr.oop.group4.chess.core.game

class GameOverException(
  drawReason: DrawReason,
) : Exception(
  "The game ended in a DRAW, due to $drawReason."
)