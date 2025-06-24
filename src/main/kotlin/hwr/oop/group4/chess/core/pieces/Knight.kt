package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.KnightJump
import hwr.oop.group4.chess.core.pieces.ValidPieceLocationGenerator.calculatePossibleLocationsToJump
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

  override fun getColor(): Color = color

  override fun getName(): String = name

  override fun getValue(): Int = value

  override fun getPossibleLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {
    return calculatePossibleLocationsToJump(from, board, knightJumps, capture)
  }
}