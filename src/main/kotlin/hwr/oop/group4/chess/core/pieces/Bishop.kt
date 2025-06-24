package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.pieces.ValidPieceLocationGenerator.calculatePossibleLocationsToMove
import hwr.oop.group4.chess.core.utils.Color

data class Bishop(private val color: Color) : Piece {
  private val name = "Bishop"
  private val value = 3
  private val directions = listOf(
    Direction.TOP_LEFT,
    Direction.TOP_RIGHT,
    Direction.BOTTOM_LEFT,
    Direction.BOTTOM_RIGHT
  )

  override fun getColor(): Color = color

  override fun getName(): String = name

  override fun getValue(): Int = value

  override fun getPossibleLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location> {
    return calculatePossibleLocationsToMove(from, board, directions, capture, 8)
  }
}