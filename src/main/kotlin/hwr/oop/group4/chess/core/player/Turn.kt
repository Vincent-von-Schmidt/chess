package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.pieces.Color

class Turn(private val players: List<Player>) {

    lateinit var white: Player
    lateinit var black: Player
    var currentPlayer = white

    fun validatePlayers(){
        white = players.find { it.color == Color.WHITE } ?: throw IllegalStateException("Missing WHITE player")
        black = players.find { it.color == Color.BLACK } ?: throw IllegalStateException("Missing BLACK player")
        // TODO validate if players are part of game to play
    }

    fun switchTurn(){
        currentPlayer = if (currentPlayer == white) { black
        } else white
    }
}