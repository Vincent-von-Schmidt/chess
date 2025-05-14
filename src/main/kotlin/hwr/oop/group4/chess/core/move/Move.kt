package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

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
