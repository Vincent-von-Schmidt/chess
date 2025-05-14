package core.move

import core.board.Board
import core.location.Location
import core.pieces.Piece

class Move (
    val startLocation: Location,
    val endLocation: Location,
    val movingPiece: Piece
) {
    private val piece = movingPiece.description
    private val startLoc = startLocation.description
    private val endLoc = endLocation.description

    fun validateMoveOn(board: Board) {
        if (board.getField(startLocation).piece != movingPiece) {
            throw IllegalArgumentException("$startLoc does not contain a $piece")
        }

        if (endLocation !in movingPiece.allowedLocations(startLocation, board)) {
            throw IllegalArgumentException("$piece can not be moved to $endLoc")
        }
    }
}
