package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.game.Game
import hwr.oop.group4.chess.core.game.GameState

interface GamePersistencePort {
  fun loadGame(id: Int): Game
  fun saveGame(game: Game, newGame: Boolean = true, gameState: GameState? = null): Game
  fun deleteGame(id: Int)
}