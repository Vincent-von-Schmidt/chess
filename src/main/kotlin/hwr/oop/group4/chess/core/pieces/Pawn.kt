package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveValidGenerator
import hwr.oop.group4.chess.core.utils.Color

data class Pawn(override val color: Color) : Piece {
  override val name = "Pawn"
  override var directions = emptyList<Direction>(
  )
  private var captureDirections = emptyList<Direction>(
  )
  // override val value = 1

  private val whitePawnDirections = listOf(Direction.TOP)
  private val whitePawnCaptures =
    listOf(Direction.TOP_RIGHT, Direction.TOP_LEFT)

  private val blackPawnDirections = listOf(Direction.BOTTOM)
  private val blackPawnCaptures =
    listOf(Direction.BOTTOM_RIGHT, Direction.BOTTOM_LEFT)

  override fun availableLocationsToMove(
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
      MoveValidGenerator().searchAllowedLocations(from, board, captureDirections, 1)
    } else if ((from.rank == Rank.TWO && color == Color.WHITE) || (from.rank == Rank.SEVEN && color == Color.BLACK)) {
      MoveValidGenerator().searchAllowedLocations(from, board, directions, 2)
    } else {
      MoveValidGenerator().searchAllowedLocations(from, board, directions, 1)
    }
  }
}