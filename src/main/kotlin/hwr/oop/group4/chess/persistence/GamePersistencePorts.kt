package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.Game

interface LoadGamePort {
  fun loadGame(id: Int): Game
}

interface SaveGamePort {
  fun saveGame(game: Game, newGame: Boolean = true)
}

interface DeleteGamePort {
  fun deleteGame(game: Game)
}
