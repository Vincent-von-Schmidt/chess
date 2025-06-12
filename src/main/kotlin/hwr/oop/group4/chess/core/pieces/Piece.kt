package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.utils.Color

sealed interface Piece {

  fun getColor(): Color
  fun getName(): String
  fun getValue(): Int
  fun getDescription(): String = "${getColor()} ${getName()}"

  fun getPossibleLocationsToMove(
    from: Location,
    board: BoardView,
    capture: Boolean,
  ): List<Location>
}