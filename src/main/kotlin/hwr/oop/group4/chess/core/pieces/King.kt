package core.pieces

import core.board.Board
import core.location.Location
import core.move.Direction

class King(color: Color): Piece {
    override val name = "King"
    override val value = 10
    override val color = color
    override val directions = listOf(
        Direction.TOP_LEFT,
        Direction.TOP,
        Direction.TOP_RIGHT,
        Direction.RIGHT,
        Direction.BOTTOM_RIGHT,
        Direction.BOTTOM,
        Direction.BOTTOM_LEFT,
        Direction.LEFT)

    override fun allowedLocations(from: Location, board: Board): List<Location> {
        return searchAllowedLocations(from, board, directions, 1)
    }
}
