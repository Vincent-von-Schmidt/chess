package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Color

object LoaderFEN {

  fun placePieces(piecePlacement: List<String>, board: Board) {
    var location = Location(File.A, Rank.EIGHT) // root of board

    for (rank in piecePlacement) {  // ranks = ["rnbqkbnr", "pppppppp", "8", "8", "8", "8", "PPPPPPPP", "RNBQKBNR"
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
            throw InvalidPiecePlacementException(piecePlacement)
          }
          val next = board.nextField(location)
          location = next.location
        }
      }
    }
  }
}
