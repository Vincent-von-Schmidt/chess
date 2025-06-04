package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.StringParser

object GeneratorFEN {

  fun generateFen(
    board: Board,
    castle: String,
    enPassant: String,
    halfMoveClock: Int,
    fullMoveNumber: Int,
    color: Color,
  ): String {
    var fen = ""
    fen += genPiecePlacement(board)
    fen += if (color == Color.WHITE) " w" else " b"
    fen += if (castle.isNotEmpty()) " $castle" else " -"
    fen += if (enPassant.isNotEmpty()) " $enPassant" else " -"
    fen += " $halfMoveClock $fullMoveNumber"
    return fen
  }

  private fun genPiecePlacement(board: Board): String {
    val fen = StringBuilder()

    for (rank in Rank.values().reversed()) {
      var emptyCount = 0

      for (file in File.values()) {
        val location = Location(file, rank)
        val piece = board.getField(location).piece

        if (piece == null) emptyCount++ else {
          if (emptyCount > 0) {
            fen.append(emptyCount)
            emptyCount = 0
          }
          fen.append(StringParser.parsePieceCharFromPiece(piece))
        }
      }

      if (emptyCount > 0) fen.append(emptyCount)
      if (rank != Rank.ONE) fen.append("/")
    }

    return fen.toString()
  }
}
