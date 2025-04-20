package group4.chess

import group4.chess.board.Board
import group4.chess.player.Player

class Game (val id: Int) {
    val board: Board = Board()

    fun newGame(fen: String){

    }

    val array: Array<Player> = arrayOf(Player(1), Player(2))
    var last_turn: Move = Move()
}