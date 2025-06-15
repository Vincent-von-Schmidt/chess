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
  private var directions = emptyList<Direction>()
  private var captureDirections = emptyList<Direction>()

  private val whitePawnDirections = listOf(Direction.TOP)
  private val whitePawnCaptures =
    listOf(Direction.TOP_RIGHT, Direction.TOP_LEFT)

  private val blackPawnDirections = listOf(Direction.BOTTOM)
  private val blackPawnCaptures =
    listOf(Direction.BOTTOM_RIGHT, Direction.BOTTOM_LEFT)

  override fun getColor(): Color = color

  override fun getName(): String = name

  override fun getValue(): Int = value

  override fun getPossibleLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {
    if (color == Color.WHITE) {
      directions = whitePawnDirections
      captureDirections = whitePawnCaptures
    } else if (color == Color.BLACK) {
      directions = blackPawnDirections
      captureDirections = blackPawnCaptures
    }

    return if (capture) {
      calculatePossibleLocationsToMove(from, board, captureDirections, 1)
    } else if ((from.rank == Rank.TWO && color == Color.WHITE) || (from.rank == Rank.SEVEN && color == Color.BLACK)) {
      calculatePossibleLocationsToMove(from, board, directions, 2)
    } else {
      calculatePossibleLocationsToMove(from, board, directions, 1)
    }
  }
}