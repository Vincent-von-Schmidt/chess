package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.KnightJump
import hwr.oop.group4.chess.core.move.MoveGenerator
import hwr.oop.group4.chess.core.utils.Color

data class Knight(override val color: Color) : Piece {
  override val name = "Knight"
  override val directions = emptyList<Direction>()
  private val knightJumps = listOf(
    KnightJump.TOP_LEFT_LEFT,
    KnightJump.TOP_TOP_LEFT,
    KnightJump.TOP_TOP_RIGHT,
    KnightJump.TOP_RIGHT_RIGHT,
    KnightJump.BOTTOM_RIGHT_RIGHT,
    KnightJump.BOTTOM_BOTTOM_RIGHT,
    KnightJump.BOTTOM_BOTTOM_LEFT,
    KnightJump.BOTTOM_LEFT_LEFT
  )
  // override val value = 3

  override fun availableLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {
    return MoveGenerator().searchJumpLocations(from, board, knightJumps)
  }
}