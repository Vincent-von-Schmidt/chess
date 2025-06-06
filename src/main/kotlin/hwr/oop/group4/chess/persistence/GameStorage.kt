package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.ParserFEN
import java.io.File

class GameStorage : GamePersistencePort {

  override fun saveGame(game: Game, newGame: Boolean): Game {
    val id = game.id
    val fen = game.fen
    val fenStrings: List<FEN>
    if (newGame && File("games/$id.csv").exists())
      throw GameExistenceException("Game with ID $id already exists.")
    else fenStrings = saveGameToFile(id, fen)

    val savedGame = Game(id, fen)
    savedGame.recentFens = fenStrings.toMutableList()
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
  ): List<FEN> {
    File("games").mkdir()
    val file = File("games/$id.csv")
    val needsNewline = file.length() > 0 && !file.readText().endsWith("\n")
    if (needsNewline) file.appendText("\n")
    val fenAsString = fen.toString()
    file.appendText("$fenAsString\n")

    val fenList = mutableListOf<FEN>()
    for (fenString in file.readLines()) {
      fenList.add(ParserFEN.parseStringToFen(fenString))
    }
    return fenList
  }

  private fun loadGameFromFile(id: Int): FEN {
    val file = File("games/$id.csv")
    if (!file.exists()) throw GameExistenceException("Game with ID $id does not exist.")
    return ParserFEN.parseStringToFen(file.readLines().last())
  }
}