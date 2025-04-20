package group4.chess

import group4.chess.board.Location
import group4.chess.board.Board
import group4.chess.pieces.*

// so sieht ein string aus: "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w 0 1" groß weiß, klein schawarz, zahl:freie felder, w für wessen zug es ist, 50züge remis regel zähler, zugzähler

class FenLoader {
    private fun parsePiece(char: Char): Piece {
        val color = if (char.isUpperCase()) Color.WHITE else Color.BLACK
        return when (char.lowercaseChar()) {
            'b' -> Bishop(color)
            'k' -> King(color)
            'q' -> Queen(color)
            'n' -> Knight(color)
            'r' -> Rook(color)
            'p' -> Pawn(color)
            else -> throw IllegalArgumentException("Unbekanntes Zeichen: $char")
        }
    }

    fun placePieces(fen : String, board: Board) {
        // for columns in rows set piece
        val rows = fen.split("/") // rows = ["rnbqkbnr", "pppppppp", "8", "8", "8", "8", "PPPPPPPP", "RNBQKBNR"]
        var y = 8

        for (row in rows) {
            var x = 'a'
            for (char in row) {
                if (char.isDigit()) {
                    x += char.toString().toInt() // direkt zu int geht nicht?? // versetze nach rechts Anzahl an lehren kästchen
                } else {
                    val piece = parsePiece(char)
                    val location = Location(x, y)
                    board.setPieceToField(location, piece)
                    x++
                }
            }
            y--
        }
    }
}