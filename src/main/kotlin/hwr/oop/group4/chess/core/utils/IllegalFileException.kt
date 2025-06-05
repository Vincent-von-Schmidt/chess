package hwr.oop.group4.chess.core.utils

class IllegalFileException(fileChar: Char) : Exception(
  "Invalid file character: $fileChar"
)