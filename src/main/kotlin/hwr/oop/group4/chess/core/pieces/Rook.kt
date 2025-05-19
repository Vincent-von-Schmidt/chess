package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction

class Rook(override val color: Color) : Piece {
  override val name = "Rook"
  override val value = 5
  override val directions = listOf(
    Direction.TOP,
    Direction.RIGHT,
    Direction.BOTTOM,
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
