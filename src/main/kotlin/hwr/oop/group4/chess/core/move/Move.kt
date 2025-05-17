package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Player

class Move (
    val startLocation: Location,
    val endLocation: Location,
    val movingPiece: Piece
) {
    private val piece = movingPiece.description
    private val startLoc = startLocation.description
    private val endLoc = endLocation.description

    fun validateMove(board: Board, playerAtTurn: Player) {
        if (board.getField(startLocation).piece != movingPiece) throw WrongPieceException(startLoc, piece)
        if (endLocation !in movingPiece.allowedLocations(startLocation, board)) throw InvalidMoveException(piece, endLoc)
        if (movingPiece.color != playerAtTurn.color) throw WrongTurnException(playerAtTurn.color)

        val occupyingPiece = board.getField(endLocation).piece

        if (occupyingPiece != null) {
            if (occupyingPiece.color == playerAtTurn.color) throw SameColorCaptureException(endLoc, occupyingPiece)
            else {} // TODO("Move the piece to the new location and remove the captured piece from the board")
        } else  {} // TODO("Move the piece to the new location")
    }
}

class SameColorCaptureException(endLoc: String, occupyingPiece: Piece) : Exception(
    "$endLoc is already occupied with ${occupyingPiece.description}."
)

class WrongTurnException(color: Color) : Exception(
    "It is the turn of $color."
)

class InvalidMoveException(piece: String, endLoc: String) : Exception(
    "$piece can not be moved to $endLoc."
)

class WrongPieceException(startLoc: String, piece: String) : Exception(
    "You can not move a $piece from $startLoc, because there is no $piece at this location."
)