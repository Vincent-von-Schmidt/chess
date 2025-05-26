package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank

object StringParser {
  fun parseLocation(input: String): Location {
    require(input.length == 2) { "Invalid location format: must be exactly 2 characters" }

    val fileChar = input[0].uppercaseChar()
    val rankChar = input[1]

    val file = try {
      File.valueOf(fileChar.toString())
    } catch (e: IllegalArgumentException) {
      throw IllegalArgumentException("Invalid file character: $fileChar")
    }

    val rank =
      Rank.values().firstOrNull { it.number.toString() == rankChar.toString() }
        ?: throw IllegalArgumentException("Invalid rank character: $rankChar")

    return Location(file, rank)
  }
}