package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board

object BoardFactory {
    fun generateBoardWithPieces(toLoadFen: FEN): Board {
        val board = Board()
        val piecePlacementMap = ParserFEN.convertPiecePlacementToMap(toLoadFen)
        board.initializeWithPieces(piecePlacementMap)
        return board
    }
}