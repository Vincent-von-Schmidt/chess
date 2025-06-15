package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.utils.Color

data class Player(private val id: Int, private val color: Color) {

  fun getColor(): Color = this.color

  fun getId(): Int = this.id
}