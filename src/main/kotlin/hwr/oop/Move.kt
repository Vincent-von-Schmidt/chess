package hwr.oop

import hwr.oop.board.Location
import hwr.oop.pieces.Piece
import hwr.oop.pieces.Queen

class Move (
) {
    val start_location: Location = Location('E', 4)
    val end_location: Location = Location('O', 4)
    val piece: Piece = Queen()
    val last_turn: Move = Move()

    fun update_board() {}
    fun validate(): Boolean {
        return true
    }
}