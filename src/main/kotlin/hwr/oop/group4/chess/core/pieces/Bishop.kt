package core.pieces

import core.board.Board
import core.location.Location
import core.move.Direction

class Bishop(color: Color): Piece {
    override val name = "Bishop"
    override val color = color
    override val value = 3
    override val directions = listOf(
        Direction.TOP_LEFT,
        Direction.TOP_RIGHT,
        Direction.BOTTOM_LEFT,
        Direction.BOTTOM_RIGHT)

    override fun allowedLocations(from: Location, board: Board): List<Location> {
        return searchAllowedLocations(from, board, directions)
    }
}
