package hwr.oop

import hwr.oop.board.Location
import hwr.oop.pieces.Piece

class Move (
    val start_location: Location,
    val end_location: Location,
    val piece: Piece,
    val last_turn: Move
) {
    fun update_board() {}
    fun validate(): Boolean {}
}