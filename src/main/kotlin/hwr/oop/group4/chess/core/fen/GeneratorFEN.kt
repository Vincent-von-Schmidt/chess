package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.*

class GeneratorFEN {
  private fun parsePiece(piece: Piece): Char {
    val isUppercase: Boolean = piece.color == Color.WHITE
    val char = when (piece) {
      is Bishop -> 'b'
      is King -> 'k'
      is Queen -> 'q'
      is Knight -> 'n'
      is Rook -> 'r'
      is Pawn -> 'p'
    }
    return if (isUppercase) char.uppercaseChar() else char
  }

  fun generateFEN(board: Board): String {
    val fen = StringBuilder()

    for (rank in 8 downTo 1) {
      var emptyCount = 0

      for (file in File.values()) {
        val location = Location(file, rank)
        val piece = board.getField(location).piece

        if (piece == null) {
          emptyCount++
        } else {
          if (emptyCount > 0) {
            fen.append(emptyCount)
            emptyCount = 0
          }
            fen.append(parsePiece(piece))
        }
      }

      if (emptyCount > 0) {
        fen.append(emptyCount)
      }
      if (rank > 1) fen.append("/")
    }

    return "$fen w - - 0 1" //pieceplacement and dummy fen stuff
  }
}