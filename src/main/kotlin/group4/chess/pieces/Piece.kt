package group4.chess.pieces

abstract class Piece(val value: Int, val color: Color) {
    abstract val name: String

    override fun toString(): String {
        return "$color $name"
    }
}