package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.Jump
import hwr.oop.group4.chess.core.move.MoveGenerator

class Knight(override val color: Color) : Piece {
    override val name = "Knight"
    override val value = 3
    override val directions = emptyList<Direction>()
    val jumps = listOf(
        Jump.TOP_LEFT_LEFT,
        Jump.TOP_TOP_LEFT,
        Jump.TOP_TOP_RIGHT,
        Jump.TOP_RIGHT_RIGHT,
        Jump.BOTTOM_RIGHT_RIGHT,
        Jump.BOTTOM_BOTTOM_RIGHT,
        Jump.BOTTOM_BOTTOM_LEFT,
        Jump.BOTTOM_LEFT_LEFT
    )

    override fun allowedLocations(from: Location, board: Board, capture: Boolean): List<Location> {
        return MoveGenerator().searchJumpLocations(from, board, jumps)
    }
}