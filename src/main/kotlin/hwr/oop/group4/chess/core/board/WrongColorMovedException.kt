package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.pieces.Piece

class WrongColorMovedException(movingPiece: Piece) : Exception(
  "You can not move a ${movingPiece.color} piece"
)