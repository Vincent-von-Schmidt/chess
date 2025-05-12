package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.location.Location

interface Piece {
    val name: String
    val value: Int
    val color: Color

    // fun allowedMoves(from: Location): List<Location>

    // fun generateAllowedMoves(from: Location, directions: List<Pair<Int, Int>>, maxSteps:Int = 8): List<Location> {
    //     val possibleMoves = mutableListOf<Location>()

    //     for ((dx, dy) in directions) {
    //         for (step in 1..maxSteps) {
    //             val newX = (from.x.code + dx * step).toChar() //doppelte umformung wegen mathematik mit char
    //             val newY = from.y + dy * step

    //             if (newX in 'a'..'h' && newY in 1..8) {
    //                 possibleMoves.add(Location(newX, newY))
    //             } else { // wird nie erreicht wegen location- bedingungen. nicht Testbar!
    //                 break
    //             }
    //         }
    //     }
    //     return possibleMoves
    // }
}
