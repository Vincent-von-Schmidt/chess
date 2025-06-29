package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.ParserFEN

object BoardFactory {
  fun generateBoardFromFen(toLoadFen: FEN): Board {
    val piecePlacementMap = ParserFEN.convertPiecePlacementToMap(toLoadFen)
    val board = Board(piecePlacementMap)
    return board
  }
}