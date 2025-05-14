package core.pieces

import core.board.Board
import core.location.Location
import core.move.Direction

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
