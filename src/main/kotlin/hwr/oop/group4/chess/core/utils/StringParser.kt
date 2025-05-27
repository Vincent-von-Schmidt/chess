package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank

object StringParser {
  fun parseLocation(input: String): Location {
    if (input.length != 2) throw InvalidLocationFormatException()

    val fileChar = input[0].uppercaseChar()
    val rankChar = input[1]

    val file = try {
      File.valueOf(fileChar.toString())
    } catch (e: IllegalArgumentException) {
      throw IllegalFileException(fileChar)
    }

    val rank =
      Rank.values().firstOrNull { it.number.toString() == rankChar.toString() }
        ?: throw IllegalRankException(rankChar)

    return Location(file, rank)
  }
}

class IllegalRankException(rankChar: Char) : Exception(
  "Illegal rank character: $rankChar"
)

class IllegalFileException(fileChar: Char) : Exception(
  "Invalid file character: $fileChar"
)

class InvalidLocationFormatException : Exception(
  "Invalid location format: must be exactly 2 characters"
)
