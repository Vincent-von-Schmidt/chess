package hwr.oop.pieces

enum class Color {
    WHITE, BLACK
}

open class Piece(val value: Int, val color: Color) {
}