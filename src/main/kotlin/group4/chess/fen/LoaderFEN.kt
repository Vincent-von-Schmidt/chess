package group4.chess.fen

import group4.chess.location.Location
import group4.chess.board.Board
import group4.chess.pieces.*

class LoaderFEN {
    private fun parsePiece(char: Char): Piece {
        val color = if (char.isUpperCase()) Color.WHITE else Color.BLACK
        return when (char.lowercaseChar()) {
            'b' -> Bishop(color)
            'k' -> King(color)
            'q' -> Queen(color)
            'n' -> Knight(color)
            'r' -> Rook(color)
            'p' -> Pawn(color)
            else -> throw IllegalArgumentException("Unknown char: $char")
        }
    }

    // TODO
    // fun placePieces(fen: ReaderFEN, board: Board) {
    //     var y = 8

    //     for (row in fen.piecePlacement) {  // rows = ["rnbqkbnr", "pppppppp", "8", "8", "8", "8", "PPPPPPPP", "RNBQKBNR"
    //         var x = 'a'
    //         for (char in row) {
    //             if (char.isDigit()) {
    //                 x += char.toString().toInt() // direkt zu int geht nicht?? // versetze nach rechts Anzahl an lehren kästchen
    //             } else {
    //                 val piece = parsePiece(char)
    //                 val location = Location(x, y)
    //                 board.setPieceToField(location, piece)
    //                 x++
    //             }
    //         }
    //         y--
    //     }
    //     // hier kann en passent rein und sowas
    // }
}