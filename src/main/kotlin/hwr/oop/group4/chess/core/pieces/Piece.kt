package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction

interface Piece {
    val name: String
    val color: Color
    val description: String get() = "$color $name"
    val value: Int
    val directions: List<Direction>

    fun allowedLocations(from: Location, board: Board): List<Location>

    fun searchAllowedLocations(from: Location, board: Board, directions: List<Direction>, maxSteps:Int = 8): List<Location> {
        val possibleLocations = mutableListOf<Location>()

        for (direction in directions) { //TODO interrupt path, if there is a piece in the way
            var current = from
            for (step in 1..maxSteps) {
                val field = board.getField(current)
                val nextField = direction.move(field) ?: break
                current = nextField.location
                possibleLocations.add(current)
            }
        }
        return possibleLocations
    }
}