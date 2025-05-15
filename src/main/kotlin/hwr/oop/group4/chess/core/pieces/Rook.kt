package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.pieces.Piece

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
