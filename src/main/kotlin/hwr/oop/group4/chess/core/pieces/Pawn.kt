package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveGenerator
import hwr.oop.group4.chess.core.utils.Color

data class Pawn(override val color: Color) : Piece {
  override val name = "Pawn"
  override val value = 1
  override val directions = mutableListOf(
    Direction.TOP,
    Direction.BOTTOM
  )
  private val captureDirections = mutableListOf(
    Direction.TOP_RIGHT,
    Direction.TOP_LEFT,
    Direction.BOTTOM_RIGHT,
    Direction.BOTTOM_LEFT
  )

  override fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location> {
    if (color == Color.WHITE) {
      captureDirections.remove(Direction.BOTTOM_LEFT)
      captureDirections.remove(Direction.BOTTOM_RIGHT)
      directions.remove(Direction.BOTTOM)
    } else {
      captureDirections.remove(Direction.BOTTOM_LEFT)
      captureDirections.remove(Direction.BOTTOM_RIGHT)
      directions.remove(Direction.TOP)
    }
    return if (capture) {
      MoveGenerator().searchAllowedLocations(from, board, captureDirections, 1)
    } else {
      MoveGenerator().searchAllowedLocations(from, board, directions, 1)
    }
  }
}