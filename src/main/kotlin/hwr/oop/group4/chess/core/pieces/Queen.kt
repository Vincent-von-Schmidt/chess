package core.pieces

import core.board.Board
import core.location.Location
import core.move.Direction
import core.pieces.Color
import core.pieces.Piece

class Queen(color: Color): Piece {
    override val name = "Queen"
    override val value = 9
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
        return searchAllowedLocations(from, board, directions)
    }
}
