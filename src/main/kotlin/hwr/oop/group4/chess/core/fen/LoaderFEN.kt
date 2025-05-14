package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.*
import hwr.oop.group4.chess.core.pieces.*

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
    
     fun placePieces(fen: ReaderFEN, board: Board) {
         var location = Location(File.A, 8) // root of board

         for (rank in fen.piecePlacement) {  // rows = ["rnbqkbnr", "pppppppp", "8", "8", "8", "8", "PPPPPPPP", "RNBQKBNR"
             for (char in rank) {
                 if (char.isDigit()) {
                     repeat(char.digitToInt()) {
                         val next = board.nextField(location)
                         location = next.location
                     }
                 } else {
                     board.setPieceToField(location, parsePiece(char))
                     val next = board.nextField(location)
                     location = next.location
                 }
             }
         }
          //hier kann en passent rein und sowas
     }
}
