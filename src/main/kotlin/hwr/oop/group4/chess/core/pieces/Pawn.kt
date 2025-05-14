package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction

class Pawn(color: Color): Piece {
    override val name = "Pawn"
    override val value = 1
    override val color = color
    override val directions = mutableListOf(
        Direction.BOTTOM,
        Direction.TOP)

    override fun allowedLocations(from: Location, board: Board): List<Location> {

        if (color == Color.WHITE) {
            directions.removeLast()
        } else directions.removeFirst()
        return searchAllowedLocations(from, board, directions)
    }
}
