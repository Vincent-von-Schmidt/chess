package group4.chess.move

import group4.chess.board.Location
import group4.chess.board.Board
import group4.chess.pieces.Piece

class Move (
    private val board: Board,
    private val startLocation: Location,
    private val endLocation: Location,
    private val toMovePiece: Piece
) {

    private val isCorrectPiece: Boolean
        get() = board.getField(startLocation).piece == toMovePiece

    private val isLegalMove: Boolean
        get() = endLocation in toMovePiece.allowedMoves(startLocation)

    fun movePiece() {
        if (!isCorrectPiece) throw IllegalArgumentException("$startLocation does not contain a $toMovePiece")
        if (!isLegalMove) throw IllegalArgumentException("$toMovePiece can not be moved to $endLocation")
        board.setPieceToField(endLocation, toMovePiece)
        board.removePieceFromField(startLocation)
    }
 /*
   val last_turn: Move = Move()

   fun validate(): Boolean {

        // hier 2 sachen, erst ob figur sich so bewegen kann, dann ob das schach oder mat ändert
       return true
   }
 */
}