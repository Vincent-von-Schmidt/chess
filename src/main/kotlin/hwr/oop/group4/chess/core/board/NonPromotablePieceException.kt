package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.pieces.Piece

class NonPromotablePieceException(promoteTo: Piece) : Exception(
  "You can not promote a ${promoteTo.description}."
)