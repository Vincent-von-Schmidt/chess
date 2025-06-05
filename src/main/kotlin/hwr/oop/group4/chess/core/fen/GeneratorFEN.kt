package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.StringParser

object GeneratorFEN {

  fun generateFen(
    board: Board,
    castle: String,
    enPassant: String,
    halfMoves: Int,
    fullMoves: Int,
    activeColor: Color,
  ): FEN {
    val piecePlacement = genPiecePlacement(board) // returns List<String>
    return FEN(
      piecePlacement = piecePlacement,
      activeColor = activeColor,
      castle = castle,
      enPassant = enPassant,
      halfMoves = halfMoves,
      fullMoves = fullMoves
    )
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
