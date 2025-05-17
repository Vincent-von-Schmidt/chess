package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.pieces.Color

data class ReaderFEN (val notation: String) {

    /*
    "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
    "1                                           2 3    4  5 6"
    1: piece placement for each row
        (capital letters for white, lowercase for black)
        (numbers for empty squares)
    2: active color (white or black)
    3: castling availability
        K/k: king side
        Q/q: queen side
    4: en passant target square
        ("-" if none)
    5: half move clock
    6: full move number
     */

    private val parts: List<String> = notation.split(" ")

    init {
        if (parts.size != 6) throw EmptyFenException()
    }

    fun getPiecePlacement(): List<String> = parts.elementAt(0).split("/")
    fun getActiveColor(): Color {
        return when (val activeColor = parts.elementAt(1)) {
            "w" -> Color.WHITE
            "b" -> Color.BLACK
            else -> throw InvalidColorException(activeColor)
        }
    }
    fun getCastle(): String = notation.split(" ").elementAt(2)
    fun getEnPassant(): String = notation.split(" ").elementAt(3)
    fun getHalfMoves(): Int = notation.split(" ").elementAt(4).toInt()
    fun getFullMoves(): Int = notation.split(" ").elementAt(5).toInt()
}

class EmptyFenException : Exception(
    """
    FEN string is empty
    """.trimIndent()
)

class InvalidColorException(activeColor: String) : Exception(
    """
    Invalid color: $activeColor
    Active color must be either 'w' or 'b'
    """.trimIndent()
)