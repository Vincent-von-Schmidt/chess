package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.game.Game
import hwr.oop.group4.chess.core.game.GameState
import java.io.File

object GameStorage : GamePersistencePort {

  override fun saveGame(game: Game, newGame: Boolean, whitePoints : Int?, blackPoints: Int?, gameState: GameState?): Game {
    val id = game.id
    val fen = game.getFen()
    val fenEntries: List<FEN>
    val saveEntries: List<SaveEntry>

    if (newGame && File("games/$id.csv").exists())
      throw GameExistenceException("Game with ID $id already exists.")
    else saveEntries = saveGameToFile(id, fen, whitePoints, blackPoints, gameState)
    fenEntries = saveEntries.map { it.getFen() }

    val savedGame = Game(id, fen)
    savedGame.recentFENs = fenEntries.toMutableList()
    return savedGame
  }

  override fun loadGame(id: Int): Game {
    val fen: FEN = loadGameFromFile(id)
    return Game(id, fen = fen)
  }

  override fun deleteGame(game: Game) {
    val id = game.id
    val file = File("games/$id.csv")
    if (file.exists()) file.delete()
  }

  private fun saveGameToFile(
    id: Int,
    fen: FEN,
    whitePoints: Int?,
    blackPoints: Int?,
    gameState: GameState?
  ): List<SaveEntry> {
    File("games").mkdir()
    val file = File("games/$id.csv")
    val needsNewline = file.length() > 0 && !file.readText().endsWith("\n")
    if (needsNewline) file.appendText("\n")

    val saveEntryAsString = SaveEntry(fen, whitePoints, blackPoints, gameState).toString()
    file.appendText("$saveEntryAsString\n")

    val saveEntryList = mutableListOf<SaveEntry>()
    for (line in file.readLines()) {
      val fenPart = line.split("|").first().trim()
      saveEntryList.add(ParserSaveEntry.parseStringToSaveEntry(fenPart))
    }
    return saveEntryList
  }

  private fun loadGameFromFile(id: Int): FEN {
    val file = File("games/$id.csv")
    if (!file.exists()) throw GameExistenceException("Game with ID $id does not exist.")
    return ParserFEN.parseStringToFen(file.readLines().last())
  }
}