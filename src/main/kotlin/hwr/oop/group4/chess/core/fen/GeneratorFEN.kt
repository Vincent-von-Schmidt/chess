package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.StringParser

object GeneratorFEN {

  fun generateFen(
    board: BoardView,
    castle: String,
    enPassant: String,
    halfMoves: Int,
    fullMoves: Int,
    activeColor: Color,
  ): FEN {
    val piecePlacement = genPiecePlacement(board) // returns List<String>
    return FEN(
      piecePlacement,
      activeColor,
      castle,
      enPassant,
      halfMoves,
      fullMoves
    )
  }

  private fun genPiecePlacement(board: BoardView): String {
    val fen = StringBuilder()
    var emptyCount = 0
    var currentRank = Rank.EIGHT

    for (field in board) {
      val location = field.location

      if (location.file == File.A && location.rank != currentRank) { //when im at first field of next rank
        if (emptyCount > 0) {
          fen.append(emptyCount)
          emptyCount = 0
        }
        fen.append("/")
        currentRank = location.rank
      }

      val piece = board.getPiece(location)
      if (piece == null) {
        emptyCount++
      } else {
        if (emptyCount > 0) {
          fen.append(emptyCount)
          emptyCount = 0
        }
        fen.append(StringParser.parsePieceCharFromPiece(piece))
      }
    }

    if (emptyCount > 0) {
      fen.append(emptyCount)
    }

    return fen.toString()
  }
}
