package hwr.oop.group4.chess.core.utils

class IllegalRankException(rankChar: Char) : Exception(
  "Illegal rank character: $rankChar"
)
