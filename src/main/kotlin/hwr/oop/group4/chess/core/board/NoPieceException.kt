package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color

class NoPieceException(
  startLoc: Location? = null,
  piece: Piece? = null,
  color: Color? = null
) : Exception(
  when {
    piece != null && startLoc != null && color == null ->
      "${startLoc.description} does not contain a {${piece.getDescription()}}"

    startLoc == null && color != null && piece is King ->
      "No king found for $color player"

    startLoc != null && piece == null && color == null ->
      "${startLoc.description} does not contain a piece"

    else -> "Required piece not found"
  }
)