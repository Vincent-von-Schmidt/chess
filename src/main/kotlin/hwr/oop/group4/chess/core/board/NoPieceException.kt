package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color

class NoPieceException(
  startLoc: Location? = null,
  piece: Piece? = null,
) : Exception(
  when {
    startLoc != null  ->
      "${startLoc.description} does not contain a piece"
    piece != null  ->
      "${piece.getDescription()} could not be found"

    else -> "Required piece not found"
  }
)