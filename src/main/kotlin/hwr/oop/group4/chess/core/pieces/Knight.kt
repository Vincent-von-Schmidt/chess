package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveGenerator

class Knight(override val color: Color) : Piece {
  override val name = "Knight"
  override val value = 3
  override val directions = listOf(
    Direction.JUMP_TOP_LEFT_LEFT,
    Direction.JUMP_TOP_TOP_LEFT,
    Direction.JUMP_TOP_TOP_RIGHT,
    Direction.JUMP_TOP_RIGHT_RIGHT,
    Direction.JUMP_BOTTOM_RIGHT_RIGHT,
    Direction.JUMP_BOTTOM_BOTTOM_RIGHT,
    Direction.JUMP_BOTTOM_BOTTOM_LEFT,
    Direction.JUMP_BOTTOM_LEFT_LEFT
  )

  override fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location> {
    return MoveGenerator().searchAllowedLocations(from, board, directions, 1)
  }
}