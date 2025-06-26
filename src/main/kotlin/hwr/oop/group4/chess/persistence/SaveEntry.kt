package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.game.GameState
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.toChar
import java.awt.Point

class SaveEntry(
  private val fen : FEN,
  private val whitePoints: Int?,
  private val blackPoints: Int?,
  private val gameState : GameState?
) {

  override fun toString(): String {
    return buildString {
      append(fen.toString())
      if (whitePoints != null || blackPoints != null || gameState != null) {
        append(" | ")
        append(whitePoints ?: 0)
        append(" ")
        append(blackPoints ?: 0)
        append(" ")
        append(gameState ?: GameState.NORMAL)
      }
    }
  }

  fun getFen() : FEN = fen
  fun getGameState(): GameState? = gameState
}