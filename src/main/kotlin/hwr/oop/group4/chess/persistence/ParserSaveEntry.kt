package hwr.oop.group4.chess.persistence


import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.game.GameState

object ParserSaveEntry {

  fun parseStringToSaveEntry(line: String): SaveEntry {
    val parts = line.split("|")
    val fenPart = parts[0].trim()
    val fen = ParserFEN.parseStringToFen(fenPart)

    val whitePoints: Int?
    val blackPoints: Int?
    val gameState: GameState?

    if (parts.size > 1) {
      val extraParts = parts[1].trim().split(",")
      val whiteRaw = if (extraParts.size > 0) extraParts[0] else null
      val blackRaw = if (extraParts.size > 1) extraParts[1] else null
      val stateRaw = if (extraParts.size > 2) extraParts[2] else null

      whitePoints = if (whiteRaw == null || whiteRaw == "0") null else whiteRaw.toIntOrNull()
      blackPoints = if (blackRaw == null || blackRaw == "-") null else blackRaw.toIntOrNull()
      gameState = if (stateRaw == null || stateRaw == "-") null else GameState.valueOf(stateRaw)
    } else {
      whitePoints = null
      blackPoints = null
      gameState = null
    }

    return SaveEntry(fen, whitePoints, blackPoints, gameState)
  }
}