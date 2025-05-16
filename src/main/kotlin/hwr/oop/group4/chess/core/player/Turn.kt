package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.pieces.Color

class Turn(players: List<Player>) {

    private val white: Player = players.find { it.color == Color.WHITE }
        ?: throw IllegalStateException("Missing WHITE player")
    private val black: Player = players.find { it.color == Color.BLACK }
        ?: throw IllegalStateException("Missing BLACK player")

    var currentPlayer: Player = white

    fun switchTurn() {
        currentPlayer = if (currentPlayer == white) black else white
    }
}