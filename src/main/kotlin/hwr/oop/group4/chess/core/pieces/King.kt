package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveValidGenerator
import hwr.oop.group4.chess.core.utils.Color

data class King(override val color: Color) : Piece {
  override val name = "King"
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
  // override val value = 10

  override fun availableLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {
    return MoveValidGenerator().searchAllowedLocations(from, board, directions, 1)
  }
}
