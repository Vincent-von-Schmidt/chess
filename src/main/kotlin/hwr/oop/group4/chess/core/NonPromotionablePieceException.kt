package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.pieces.Piece

class NonPromotionablePieceException(promoteTo: Piece) : Exception(
  "${promoteTo.description} is not a promotionable piece."
)