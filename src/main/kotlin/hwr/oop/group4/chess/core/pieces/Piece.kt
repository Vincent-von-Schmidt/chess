package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.utils.Color

sealed interface Piece {
  val name: String
  val color: Color
  val directions: List<Direction>
  val description: String get() = "$color $name"
  // val value: Int

  fun availableLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location>
}