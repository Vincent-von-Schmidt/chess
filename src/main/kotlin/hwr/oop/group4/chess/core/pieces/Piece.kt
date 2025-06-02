package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.utils.Color

// TODO: more functions, less attributes

sealed interface Piece {
  val name: String
  val color: Color
  val directions: List<Direction>
  val description: String get() = "$color $name"
  // val value: Int

  fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location>
}