package hwr.oop.group4.chess.persistence


import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.game.GameState

object ParserSaveEntry {

  fun parseStringToSaveEntry(line: String): SaveEntry {
    val parts = line.split("|")
    val fenPart = parts[0].trim()
    val fen = ParserFEN.parseStringToFen(fenPart)

    require(parts.size == 2) { "Invalid SaveEntry format: $line" }

    val extraParts = parts[1].trim().split(",")
    val whiteRaw = extraParts.getOrNull(0)
    val blackRaw = extraParts.getOrNull(1)
    val stateRaw = extraParts.getOrNull(2)

    val whitePoints = whiteRaw?.toIntOrNull() ?: 0
    val blackPoints = blackRaw?.toIntOrNull() ?: 0
    val gameState = GameState.entries.find { it.name == stateRaw } ?: GameState.NORMAL
    return SaveEntry(fen, whitePoints, blackPoints, gameState)
  }
}