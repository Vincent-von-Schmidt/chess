package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.game.Game
import java.io.File

object GameStorage : GamePersistencePort {

  override fun saveGame(game: Game, newGame: Boolean): Game {
    val id = game.id
    val saveEntries = game.getSaveEntries()

    if (newGame && File("games/$id.csv").exists())
      throw GameExistenceException("Game with ID $id already exists.")
    else saveGameToFile(id, saveEntries)

    val savedGame = Game(id, saveEntries)
    return savedGame
  }

  override fun loadGame(id: Int): Game {
    val saveEntries: List<SaveEntry> = loadGameFromFile(id)
    return Game(id, saveEntries)
  }

  override fun deleteGame(id: Int) {
    val file = File("games/$id.csv")
    if (file.exists()) file.delete()
  }

  private fun saveGameToFile(id: Int, saveEntries: List<SaveEntry>) {
    File("games").mkdir()
    val file = File("games/$id.csv")
    val needsNewline = file.length() > 0 && !file.readText().endsWith("\n")
    if (needsNewline) file.appendText("\n")

    if (!file.exists()) {
      file.writeText(saveEntries.joinToString("\n") { it.toString() } + "\n")
    } else {
      val saveEntryAsString = saveEntries.last().toString()
      file.appendText(saveEntryAsString + "\n")
    }
  }

  private fun loadGameFromFile(id: Int): List<SaveEntry> {
    val file = File("games/$id.csv")
    if (!file.exists()) throw GameExistenceException("Game with ID $id does not exist.")
    return file.readLines().map { ParserSaveEntry.parseStringToSaveEntry(it) }
  }
}