package hwr.oop.group4.chess.core.board

class NonExistentPieceException(startLoc: String) : Exception(
  "$startLoc does not contain a piece"
)