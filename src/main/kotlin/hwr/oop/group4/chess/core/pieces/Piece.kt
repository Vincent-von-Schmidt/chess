package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.utils.Color

abstract sealed class Piece {
  abstract val name: String
  abstract val color: Color
  abstract val value: Int
  abstract val directions: List<Direction>

  val description: String get() = "$color $name"


  abstract fun allowedLocations(
    from: Location,
    board: Board,
    capture: Boolean,
  ): List<Location>

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Piece) return false
    return this.description == other.description
  }

  override fun hashCode(): Int = description.hashCode()
}