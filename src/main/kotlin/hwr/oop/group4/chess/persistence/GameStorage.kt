package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE
import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import java.io.File

class GameStorage : GamePersistencePort {
  private val gamesFolder = File("games")
  private var filepath = GAMES_FILE

  override fun saveGame(game: Game, newGame: Boolean) {
    val id = game.id
    val fen = game.fen
    if (id >= TEST_NUMBER) filepath = GAMES_FILE_TEST
    if (newGame && loadGameFromFile(id, filepath) != null)
      throw GameAlreadyExistsException(id)
    else saveGameToFile(id, fen, filepath, newGame = newGame)
  }

  override fun loadGame(id: Int): Game {
    if (id >= TEST_NUMBER) filepath = GAMES_FILE_TEST
    val fen: String = loadGameFromFile(id, filepath)
      ?: throw GameDoesNotExistException(id)
    return Game(id, fen = fen)
  }

  override fun deleteGame(game: Game) {
    val id = game.id
    if (id >= TEST_NUMBER) filepath = GAMES_FILE_TEST
    val file = File(filepath)
    if (!file.exists()) return
    val lines = file.readLines().filter { !it.startsWith(id.toString()) }
    file.writeText(lines.joinToString("\n"))
  }

  private fun saveGameToFile(
    id: Int,
    fen: String,
    filepath: String,
    newGame: Boolean,
  ) {
    val file = File(filepath)
    val needsNewline = file.length() > 0 && !file.readText().endsWith("\n")
    if (needsNewline) file.appendText("\n")
    if (newGame) file.appendText("$id;$fen\n") else {

      // TODO("implement ThreefoldRepetitionRule")

      val lines = file.readLines().toMutableList()
      for (i in lines.indices) {
        if (lines[i].startsWith(id.toString())) {
          lines[i] = "$id;$fen"
          break
        }
      }
      file.writeText(lines.joinToString("\n"))
    }
  }

  private fun loadGameFromFile(id: Int, filepath: String): String? {
    gamesFolder.mkdirs()
    val file = File(filepath)
    if (!file.exists()) file.createNewFile()
    for (line in file.readLines()) {
      val parts = line.split(';')
      if (parts[0] == id.toString()) return parts[1]
    }
    return null
  }

  class GameAlreadyExistsException(id: Int) : Exception(
    "Game with ID $id already exists."
  )

  class GameDoesNotExistException(id: Int) : Exception(
    "Game with ID $id does not exist."
  )
}
