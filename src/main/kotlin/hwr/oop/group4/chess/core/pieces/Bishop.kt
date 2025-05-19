package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveGenerator

class Bishop(override val color: Color) : Piece {
  override val name = "Bishop"
  override val value = 3
  override val directions = listOf(
    Direction.TOP_LEFT,
    Direction.TOP_RIGHT,
    Direction.BOTTOM_LEFT,
    Direction.BOTTOM_RIGHT
  )

  override fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location> {
    return MoveGenerator().searchAllowedLocations(from, board, directions)
  }
}