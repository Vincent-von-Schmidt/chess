package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.pieces.Piece

class InvalidPromotionException(
  movingPiece: Piece? = null,
  promoteToPiece: Piece? = null,
) : Exception(
  when {
    movingPiece != null && promoteToPiece == null ->
      "${movingPiece.getDescription()} has no Piece to promote to"

    movingPiece != null && promoteToPiece != null ->
      "${movingPiece.getDescription()} cannot promote to ${promoteToPiece.getDescription()}"

    else ->
      """
  Valid Promotions are...
  ...Queen, Rook, Bishop, Knight.  
  """.trimIndent()
  }
)