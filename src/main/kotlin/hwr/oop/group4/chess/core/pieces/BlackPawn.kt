package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveGenerator
import hwr.oop.group4.chess.core.utils.Color

data class BlackPawn(override val color: Color) : Piece {

  init {
    if (color != Color.BLACK) throw WrongColorPawnException()
  }

  override val name = "Pawn"
  override val directions = mutableListOf(
    Direction.BOTTOM
  )
  private val captureDirections = mutableListOf(
    Direction.BOTTOM_LEFT,
    Direction.BOTTOM_RIGHT
  )
  // override val value = 1

  override fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location> {
    return if (capture) {
      MoveGenerator().searchAllowedLocations(from, board, captureDirections, 1)
    } else if (from.rank == Rank.SEVEN /* && this.color == Color.BLACK*/) { // if migrating back to one pawn
      MoveGenerator().searchAllowedLocations(from, board, directions, 2)
    } else {
      MoveGenerator().searchAllowedLocations(from, board, directions, 1)
    }
  }
}

class WrongColorPawnException : Exception(
  "Declared pawn with wrong color"
)