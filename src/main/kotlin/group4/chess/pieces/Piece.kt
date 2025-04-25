package group4.chess.pieces

import group4.chess.board.Location

abstract class Piece(val value: Int, val color: Color) {
    abstract val name: String

    override fun toString(): String {
        return "$color $name"
    }

    abstract fun allowedMoves(from: Location): List<Location>
}