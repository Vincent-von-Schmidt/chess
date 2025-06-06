package hwr.oop.group4.chess.core.utils

class IllegalPieceException(char: Char) : Exception(
  "Unknown char: $char"
)