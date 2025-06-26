package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.persistence.SaveEntry

object GameFactory {
  fun generateGameFromFen(id: Int, toLoadFen: FEN): Game {
    val gameSave = listOf(
      SaveEntry(toLoadFen, null, null, null))
    val game = Game(id, gameSave)
    return game
  }
}