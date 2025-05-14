package core.pieces

import core.board.Board
import core.location.Location
import core.move.Direction

class Knight(color: Color): Piece {
    override val name = "Knight"
    override val value = 3
    override val color = color
    override val directions = listOf(
        Direction.JUMP_TOP_LEFT_LEFT,
        Direction.JUMP_TOP_TOP_LEFT,
        Direction.JUMP_TOP_TOP_RIGHT,
        Direction.JUMP_TOP_RIGHT_RIGHT,
        Direction.JUMP_BOTTOM_RIGHT_RIGHT,
        Direction.JUMP_BOTTOM_BOTTOM_RIGHT,
        Direction.JUMP_BOTTOM_BOTTOM_LEFT,
        Direction.JUMP_BOTTOM_LEFT_LEFT)

    override fun allowedLocations(from: Location, board: Board): List<Location> {
        return searchAllowedLocations(from, board, directions, 1)
    }
}
