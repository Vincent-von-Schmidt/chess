package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.pieces.ValidPieceLocationGenerator.calculatePossibleLocationsToMove
import hwr.oop.group4.chess.core.utils.Color

data class Pawn(private val color: Color) : Piece {
  private val name = "Pawn"
  private val value = 1
  private val directions =
    if (color == Color.WHITE) listOf(Direction.TOP) else listOf(Direction.BOTTOM)
  private val captureDirections = if (color == Color.WHITE)
    listOf(Direction.TOP_LEFT, Direction.TOP_RIGHT)
  else
    listOf(Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT)

  override fun getColor(): Color = color

  override fun getName(): String = name

  override fun getValue(): Int = value

  override fun getPossibleLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {

    return if (capture) {
      calculatePossibleLocationsToMove(from, board, captureDirections, true, 1)
    } else if ((from.rank == Rank.TWO && color == Color.WHITE) || (from.rank == Rank.SEVEN && color == Color.BLACK)) {
      calculatePossibleLocationsToMove(from, board, directions, false, 2)
    } else {
      calculatePossibleLocationsToMove(from, board, directions, false, 1)
    }
  }
}