package group4.chess.pieces

import group4.chess.location.Location

class Rook(color: Color): Piece {
    override val name = "Rook"
    override val value = 5
    override val color = color

    // TODO
    // override fun allowedMoves(from: Location): List<Location> {

    //     val directions = listOf(
    //         Pair(1, 0),   // rechts
    //         Pair(-1, 0),  // links
    //         Pair(0, 1),   // oben
    //         Pair(0, -1),  // unten
    //     )

    //     return generateAllowedMoves(from, directions, 8)
    // }
}