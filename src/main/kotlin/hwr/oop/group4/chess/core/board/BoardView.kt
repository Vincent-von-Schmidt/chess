package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

interface BoardView : Iterable<Field> {
  fun getPiece(location: Location): Piece?
  fun getField(location: Location): Field
  fun simulateMoveAndCheck(
    from: Location,
    to: Location,
    piece: Piece,
    condition: () -> Boolean,
  ): Boolean

  override fun iterator(): Iterator<Field> = FieldIterator(this)
}