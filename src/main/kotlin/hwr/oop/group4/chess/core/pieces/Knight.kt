package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.KnightJump
import hwr.oop.group4.chess.core.pieces.ValidPieceLocationGenerator.searchJumpLocations
import hwr.oop.group4.chess.core.utils.Color

data class Knight(private val color: Color) : Piece {
  private val name = "Knight"
  private val value = 3
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

  override fun getColor(): Color {
    return color
  }

  override fun getName(): String {
    return name
  }

  override fun getValue(): Int {
    return value
  }

  override fun getPossibleLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {
    return searchJumpLocations(from, board, knightJumps)
  }
}