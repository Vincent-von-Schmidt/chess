package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.player.Turn

class Game ( //hier cli schnittstelle?
    //val id: Int,
    val board: Board,
    players: List<Player>
) {
    val turn = Turn(players)

    fun movePiece(move: Move){
        val playerAtTurn = turn.currentPlayer

        move.validateMove(board, playerAtTurn)
        board.movePiece(move)

        turn.switchTurn()
    }
}