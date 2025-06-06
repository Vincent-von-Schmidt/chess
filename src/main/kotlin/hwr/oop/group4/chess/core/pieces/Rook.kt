package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveGenerator
import hwr.oop.group4.chess.core.utils.Color

data class Rook(override val color: Color) : Piece {
  override val name = "Rook"
  override val directions = listOf(
    Direction.TOP,
    Direction.RIGHT,
    Direction.BOTTOM,
    Direction.LEFT
  )
  // override val value = 5

  override fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location> {
    return MoveGenerator().searchAllowedLocations(from, board, directions, 8)
  }
}
