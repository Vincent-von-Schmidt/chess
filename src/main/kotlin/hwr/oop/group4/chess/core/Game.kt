package core

import core.board.Board
import core.move.Move

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
