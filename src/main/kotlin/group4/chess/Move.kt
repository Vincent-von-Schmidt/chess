package group4.chess

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

    fun movePiece() {
        if (!isCorrectPiece) throw IllegalArgumentException("$startLocation does not contain a $toMovePiece")
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