package hwr.oop

import hwr.oop.board.Board
import hwr.oop.player.Player

class Game (
    val id: Int
) {
    val board = Board()
    val array = listOf(
        Player(1),
        Player(2)
    )
    var last_turn = Move()
}