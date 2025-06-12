package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

class InvalidMoveException(
  movingPiece: Piece? = null,
  endLoc: Location? = null,
  occupyingPiece: Piece? = null
) : Exception(
  when {

    movingPiece != null && endLoc != null && occupyingPiece == null ->
      "${movingPiece.description} can not be moved to ${endLoc.description}"

    movingPiece != null && endLoc == null && occupyingPiece == null ->
      "You cannot move a ${movingPiece.description}"

    else -> "Invalid move"
  }
)