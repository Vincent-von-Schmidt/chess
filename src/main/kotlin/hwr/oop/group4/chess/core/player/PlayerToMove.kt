package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.utils.Color

class PlayerToMove(
  private var whitePlayer: Player,
  private var blackPlayer: Player,
  private var currentPlayer: Player,
) {
  fun getCurrentColor(): Color = currentPlayer.color

  fun switchTurn() {
    currentPlayer = if (currentPlayer.color == Color.WHITE) {
      blackPlayer
    } else whitePlayer
  }
}