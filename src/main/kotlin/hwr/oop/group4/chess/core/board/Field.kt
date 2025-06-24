package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.pieces.Piece

class Field(
  val location: Location,
  private val neighborProvider: () -> Map<Direction, Field?>,  // Lazy provider
) {
  val top: Field? by lazy { neighborProvider()[Direction.TOP] }
  val bottom: Field? by lazy { neighborProvider()[Direction.BOTTOM] }
  val left: Field? by lazy { neighborProvider()[Direction.LEFT] }
  val right: Field? by lazy { neighborProvider()[Direction.RIGHT] }

  private var piece: Piece? = null

  fun getPiece() = piece
  internal fun placePiece(piece: Piece?) {
    this.piece = piece
  }
}