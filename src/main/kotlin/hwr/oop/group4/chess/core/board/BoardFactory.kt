package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION

object BoardFactory {
    fun generateBoardWithPieces(toLoadFen: FEN = STARTING_POSITION): Board {
        val board = Board() // TODO BoardView?
        val piecePlacementMap = ParserFEN.convertPiecePlacementToMap(toLoadFen)
        board.initializeWithPieces(piecePlacementMap)
        return board
    }
}