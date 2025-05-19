package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction

interface Piece {
  val name: String
  val color: Color
  val description: String get() = "$color $name"
  val value: Int
  val directions: List<Direction>

  fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location>
}