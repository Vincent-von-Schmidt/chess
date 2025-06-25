package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION

object BoardFactory {
  fun generateBoardFromFen(toLoadFen: FEN): Board {
    val piecePlacementMap = ParserFEN.convertPiecePlacementToMap(toLoadFen)
    val board = Board(piecePlacementMap)
    return board
  }
}