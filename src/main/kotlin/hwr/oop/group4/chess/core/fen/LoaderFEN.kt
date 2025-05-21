package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
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
      else -> throw IllegalPieceException(char)
    }
  }

  fun placePieces(fen: ReaderFEN, board: Board) {
    var location = Location(File.A, 8) // root of board

    for (rank in fen.piecePlacement) {  // ranks = ["rnbqkbnr", "pppppppp", "8", "8", "8", "8", "PPPPPPPP", "RNBQKBNR"
      for (char in rank) {
        if (char.isDigit()) {
          repeat(char.digitToInt()) {
            val next = board.nextField(location)
            location = next.location
          }
        } else {
          try {
            board.setPieceToField(location, parsePiece(char))
          } catch (e: IllegalPieceException) {
            throw InvalidFenException(fen)
          }
          val next = board.nextField(location)
          location = next.location
        }
      }
    }
    TODO("implement castling and en passant")
  }
}

class InvalidFenException(fen: ReaderFEN) : Exception(
  "The fen string: $fen is invalid."
)

class IllegalPieceException(char: Char) : Exception(
  "Unknown char: $char"
)
