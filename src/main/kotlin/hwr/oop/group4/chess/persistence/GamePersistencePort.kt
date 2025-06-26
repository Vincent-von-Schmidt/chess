package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.game.Game

interface GamePersistencePort {
  fun loadGame(id: Int): Game
  fun saveGame(game: Game, newGame: Boolean = true): Game
  fun deleteGame(id: Int)
}