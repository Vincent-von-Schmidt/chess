package group4.chess

import group4.chess.board.Location
import group4.chess.pieces.Color
import group4.chess.pieces.Piece
import group4.chess.pieces.Queen

class Move (
) {
    val start_location: Location = Location('E', 4)
    val end_location: Location = Location('O', 4)
    val piece: Piece = Queen(Color.WHITE)
    val last_turn: Move = Move()

    fun validate(): Boolean {

        // hier 2 sachen, erst ob figur sich so bewegen kann, dann ob das schach oder mat ändert
        return true
    }

    fun update_board() {}

}