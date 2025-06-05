package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.pieces.Piece

class NoPromoteChoiceException(movingPiece: Piece) : Exception(
  "${movingPiece.description} has no piece to promote to"
)