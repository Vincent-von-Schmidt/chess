package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.pieces.ValidPieceLocationGenerator.searchAllowedLocations
import hwr.oop.group4.chess.core.utils.Color

data class Bishop(override val color: Color) : Piece {
  override val name = "Bishop"
  override val directions = listOf(
    Direction.TOP_LEFT,
    Direction.TOP_RIGHT,
    Direction.BOTTOM_LEFT,
    Direction.BOTTOM_RIGHT
  )
  // override val value = 3

  override fun availableLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {
    return searchAllowedLocations(from, board, directions, 8)
  }
}