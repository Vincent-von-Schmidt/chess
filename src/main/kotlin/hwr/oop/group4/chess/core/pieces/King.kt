package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.location.Location

class King(color: Color): Piece {
    override val name = "King"
    override val value = 10
    override val color = color

    // TODO
    // override fun allowedMoves(from: Location): List<Location> {

    //     val directions = listOf(
    //         Pair(1, 0),   // rechts
    //         Pair(-1, 0),  // links
    //         Pair(0, 1),   // oben
    //         Pair(0, -1),  // unten
    //         Pair(1, 1),   // oben rechts
    //         Pair(1, -1),  // unten rechts
    //         Pair(-1, 1),  // oben links
    //         Pair(-1, -1)  // unten links
    //     )
    //     return generateAllowedMoves(from, directions, 1)
    // }
}
