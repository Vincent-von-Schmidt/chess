package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

class NoPieceException(
  startLoc: Location? = null,
  piece: Piece? = null,
) : Exception(
  when {
    startLoc != null ->
      "${startLoc.description} does not contain a piece"

    else -> "Required piece not found"
  }
)