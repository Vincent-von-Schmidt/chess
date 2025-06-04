package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.pieces.*

object BoardFactory {
    fun generateBoardWithPieces(toLoadFen: String): Board {
        val board = Board()
        val piecePlacementMap = FEN(toLoadFen).toPieceMap()
        board.initializeWithPieces(piecePlacementMap)
        return board
    }
}