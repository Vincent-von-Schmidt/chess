package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.StringParser

object ParserFEN {

  fun parseStringToFen(fenString: String): FEN {
    val parts = fenString.split(" ")
    return FEN(
      piecePlacement = parts[0],
      activeColor = when (parts[1]) {
        "w" -> Color.WHITE
        "b" -> Color.BLACK
        else -> throw IllegalArgumentException("Invalid color")
      },
      castle = parts[2],
      enPassant = parts[3],
      halfMoves = parts[4].toInt(),
      fullMoves = parts[5].toInt()
    )
  }

  /* "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
  groß weiß, klein schwarz,
  zahl:freie felder,
  w oder b für wessen zug es ist,
  castle-züge KQkq
  c6 ein en passant ziel feld
  50züge remis regel zähler, (halbzüge)
  zugzähler */


  fun convertPiecePlacementToMap(fen: FEN): Map<Location, Piece> {
    val map = mutableMapOf<Location, Piece>()
    var rank = Rank.EIGHT
    for (row in fen.piecePlacement.split("/")) {
      var file = File.A
      for (char in row) {
        if (char.isDigit()) {
          repeat(char.digitToInt()) {
            file = file.next() ?: return@repeat
          }
        } else {
          val piece = StringParser.parsePieceFromChar(char)
          map[Location(file, rank)] = piece
          file = file.next() ?: break
        }
      }
      rank = rank.previous() ?: break
    }
    return map
  }

}