package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.Game
import java.io.File

class GameStorage : GamePersistencePort {
  private val gamesFolder = File("games")

  override fun saveGame(game: Game, newGame: Boolean) {
    val id = game.id
    val fen = game.fen
    if (newGame && File("games/$id.csv").exists())
      throw GameExistenceException("Game with ID $id already exists.")
    else saveGameToFile(id, fen)
  }

  override fun loadGame(id: Int): Game {
    val fen: String = loadGameFromFile(id)
    return Game(id, fen = fen)
  }

  override fun deleteGame(game: Game) {
    val id = game.id
    val file = File("games/$id.csv")
    if (file.exists()) file.delete()
  }

  private fun saveGameToFile(
    id: Int,
    fen: String,
  ) {
    val file = File("games/$id.csv")
    val needsNewline = file.length() > 0 && !file.readText().endsWith("\n")
    if (needsNewline) file.appendText("\n")
    file.appendText("$fen\n")
  }

  private fun loadGameFromFile(id: Int): String {
    val file = File("games/$id.csv")
    if (!file.exists()) throw GameExistenceException("Game with ID $id does not exist.")
    return file.readLines().last()
  }
}