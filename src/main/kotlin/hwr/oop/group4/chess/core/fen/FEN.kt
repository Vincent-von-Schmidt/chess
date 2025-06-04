package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.StringParser

class FEN(private val notation: String) {
    val piecePlacement: List<String> =
        notation.split(" ").elementAt(0).split("/")
    val activeColor: Color =
        when (notation.split(" ").elementAt(1)) {
            "w" -> Color.WHITE
            "b" -> Color.BLACK
            else -> throw IllegalArgumentException("Not a valid color")
        }
    val castle: String = notation.split(" ").elementAt(2)
    val enpassant: String = notation.split(" ").elementAt(3)
    val halfmoves: Int = notation.split(" ").elementAt(4).toInt()
    val fullmoves: Int = notation.split(" ").elementAt(5).toInt()

    fun toPieceMap(): Map<Location, Piece> {
        val map = mutableMapOf<Location, Piece>()
        var rank = 8
        for (row in piecePlacement) {
            var file = 'A'
            for (char in row) {
                if (char.isDigit()) {
                    file += char.digitToInt()
                } else {

                    val piece = StringParser.parsePieceFromChar(char)
                    map[Location(file, rank)] = piece
                    file++
                }
            }
            rank--
        }
        return map
    }
}

/* "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
groß weiß, klein schwarz,
zahl:freie felder,
w oder b für wessen zug es ist,
castle-züge KQkq
c6 ein en passant ziel feld
50züge remis regel zähler, (halbzüge)
zugzähler */
