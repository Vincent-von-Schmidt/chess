package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.player.Player

class Game (
    //val id: Int,
    val board: Board,
    //val players: List<Player>
) {

    fun movePiece(move: Move){
        move.validateMoveOn(board)
        board.movePiece(move)
    }
}
