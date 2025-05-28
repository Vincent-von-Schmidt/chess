package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveGenerator
import hwr.oop.group4.chess.core.utils.Color

data class WhitePawn(override val color: Color) : Piece {

  init {
    if (color != Color.WHITE) throw WrongColorPawnException()
  }

  override val name = "Pawn"
  override val directions = mutableListOf(
    Direction.TOP
  )
  private val captureDirections = mutableListOf(
    Direction.TOP_RIGHT,
    Direction.TOP_LEFT
  )
  // override val value = 1

  override fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location> {
    return if (capture) {
      MoveGenerator().searchAllowedLocations(from, board, captureDirections, 1)
    } else {
      MoveGenerator().searchAllowedLocations(from, board, directions, 1)
    }
  }
}