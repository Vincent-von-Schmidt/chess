package hwr.oop.group4.chess.core.fen

class InvalidPiecePlacementException(piecePlacement: List<String>) : Exception(
  "The piece placement $piecePlacement is invalid."
)