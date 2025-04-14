package hwr.oop

import hwr.oop.board.Board
import hwr.oop.player.Player

class Game (val id: Int) {
    val board: Board = Board()
    val startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
    fun setPieces(fen: String, board: Board){

    }

    val array: Array<Player> = arrayOf(Player(1), Player(2))
    var last_turn: Move = Move()
}