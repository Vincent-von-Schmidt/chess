package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.utils.Color

class Turn(players: List<Player>) {

  private val white: Player = players.find { it.color == Color.WHITE }!!
  private val black: Player = players.find { it.color == Color.BLACK }!!

  var currentPlayer: Player = white

  fun switchTurn() {
    currentPlayer = if (currentPlayer == white) black else white
  }
}