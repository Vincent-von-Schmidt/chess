package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.pieces.Piece

class InvalidPromotionException(
  movingPiece: Piece? = null,
  promoteToPiece: Piece? = null,
) : Exception(
  when {
    movingPiece != null && promoteToPiece == null ->
      "${movingPiece.description} has no Piece to promote to"

    movingPiece != null && promoteToPiece != null ->
      "${movingPiece.description} cannot promote to ${promoteToPiece.description}"

    else ->
      """
  Valid Promotions are...
  ...Queen, Rook, Bishop, Knight.  
  """.trimIndent()
  }
)