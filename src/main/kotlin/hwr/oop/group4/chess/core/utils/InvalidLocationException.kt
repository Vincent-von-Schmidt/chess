package hwr.oop.group4.chess.core.utils

class InvalidLocationException(unknownChar: Char? = null) : Exception(  when {
  unknownChar != null -> "Invalid character: $unknownChar"

  else -> "Invalid location"
})