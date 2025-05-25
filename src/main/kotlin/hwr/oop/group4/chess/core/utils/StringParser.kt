package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location

class StringParser {
  fun parseLocation(input: String): Location {
    require(input.length == 2) { "Invalid location format" }

    val fileChar = input[0].uppercaseChar()
    val rankChar = input[1]

    val file = File.valueOf(fileChar.toString())
    val rank = rankChar.digitToInt()
    require(rank in 1..8) { "Invalid rank: $rankChar" }

    return Location(file, rank)
  }
}