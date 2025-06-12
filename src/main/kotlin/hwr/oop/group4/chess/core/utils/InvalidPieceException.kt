package hwr.oop.group4.chess.core.utils

class InvalidPieceException(char: Char) : Exception(
  "Unknown char: $char"
)