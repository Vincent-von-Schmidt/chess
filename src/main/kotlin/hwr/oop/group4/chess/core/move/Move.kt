package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Player

class Move(
    val startLocation: Location,
    val endLocation: Location,
    val movingPiece: Piece, // muss ausm board selbst bestimmt werden
    val capture: Boolean = false, // muss ausm board selbst bestimmt werden
) {
    private val piece = movingPiece.description
    private val startLoc = startLocation.description
    private val endLoc = endLocation.description

    fun validateMove(board: Board, playerAtTurn: Player) {
        if (board.getField(startLocation).piece != movingPiece) {
            throw IllegalArgumentException("$startLoc does not contain a $piece")
        }

        if (movingPiece.color != playerAtTurn.color) {
            throw IllegalStateException("You can not move a ${movingPiece.color} piece")
        }

        val occupyingPiece = board.getField(endLocation).piece

        if (occupyingPiece != null) { //if other color and capture, then capture. if other color: do u want to capture?
            if (occupyingPiece.color == playerAtTurn.color) {
                throw IllegalStateException("$endLoc is already occupied with ${occupyingPiece.description}")
            } else if (occupyingPiece.color != playerAtTurn.color && !capture) {
                throw IllegalStateException("$endLoc is already occupied with ${occupyingPiece.description}, do you want to capture?")
            }
        }

        if (endLocation !in movingPiece.allowedLocations(startLocation, board, capture)) {
            throw IllegalArgumentException("$piece can not be moved to $endLoc")
        }
    }
}
