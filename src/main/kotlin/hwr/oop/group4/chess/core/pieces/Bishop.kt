package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction

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
