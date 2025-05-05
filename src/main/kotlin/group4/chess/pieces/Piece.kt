package group4.chess.pieces

import group4.chess.board.Location

abstract class Piece(val value: Int, val color: Color) {
    abstract val name: String

    override fun toString(): String {
        return "$color $name"
    }

    abstract fun allowedMoves(from: Location): List<Location>

    fun generateAllowedMoves(from: Location, directions: List<Pair<Int, Int>>, maxSteps:Int = 8): List<Location> {
        val possibleMoves = mutableListOf<Location>()

        for ((dx, dy) in directions) {
            for (step in 1..maxSteps) {
                val newX = (from.x.code + dx * step).toChar() //doppelte umformung wegen mathematik mit char
                val newY = from.y + dy * step

                if (newX in 'a'..'h' && newY in 1..8) {
                    possibleMoves.add(Location(newX, newY))
                } else { // wird nie erreicht wegen location- bedingungen. nicht Testbar!
                    break
                }
            }
        }
        return possibleMoves
    }
}