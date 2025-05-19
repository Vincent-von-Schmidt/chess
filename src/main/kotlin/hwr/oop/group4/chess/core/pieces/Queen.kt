package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction

class Queen(override val color: Color) : Piece {
  override val name = "Queen"
  override val value = 9
  override val directions = listOf(
    Direction.TOP_LEFT,
    Direction.TOP,
    Direction.TOP_RIGHT,
    Direction.RIGHT,
    Direction.BOTTOM_RIGHT,
    Direction.BOTTOM,
    Direction.BOTTOM_LEFT,
    Direction.LEFT
  )

  override fun allowedLocations(from: Location, board: Board): List<Location> {
    return searchAllowedLocations(from, board, directions)
  }

  override fun allowedCaptureLocations(
    from: Location,
    board: Board,
  ): List<Location> {
    return allowedLocations(from, board)
  }
}
