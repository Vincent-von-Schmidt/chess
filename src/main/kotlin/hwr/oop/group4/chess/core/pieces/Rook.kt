package core.pieces

import core.board.Board
import core.location.Location
import core.move.Direction
import core.pieces.Color
import core.pieces.Piece

class Rook(color: Color): Piece {
    override val name = "Rook"
    override val value = 5
    override val color = color
    override val directions = listOf(
        Direction.TOP,
        Direction.RIGHT,
        Direction.BOTTOM,
        Direction.LEFT)

    override fun allowedLocations(from: Location, board: Board): List<Location> {
        return searchAllowedLocations(from, board, directions)
    }
}
