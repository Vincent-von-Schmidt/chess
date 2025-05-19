package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction

class Pawn(override val color: Color) : Piece {
    override val name = "Pawn"
    override val value = 1
    override val directions = mutableListOf(
        Direction.TOP,
        Direction.BOTTOM
    )
    private val captureDirections = mutableListOf(
        Direction.TOP_RIGHT,
        Direction.TOP_LEFT,
        Direction.BOTTOM_RIGHT,
        Direction.BOTTOM_LEFT
    )

    override fun allowedLocations(
        from: Location,
        board: Board,
    ): List<Location> {

        if (color == Color.WHITE) {
            directions.removeLast()
        } else directions.removeFirst()
        return searchAllowedLocations(from, board, directions, 1)
    }

    override fun allowedCaptureLocations(
        from: Location,
        board: Board,
    ): List<Location> {
        if (color == Color.WHITE) {
            captureDirections.remove(Direction.BOTTOM_LEFT)
            captureDirections.remove(Direction.BOTTOM_RIGHT)
        } else {
            captureDirections.remove(Direction.TOP_LEFT)
            captureDirections.remove(Direction.TOP_RIGHT)
        }
        return searchAllowedLocations(from, board, captureDirections, 1)
    }
}