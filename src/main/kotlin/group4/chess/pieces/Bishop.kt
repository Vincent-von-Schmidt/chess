package group4.chess.pieces

import group4.chess.location.Location

class Bishop(color: Color): Piece {
    override val name = "Bishop"
    override val value = 3
    override val color = color

    // TODO
    // override fun allowedMoves(from: Location): List<Location> {

    //     val directions = listOf(
    //         Pair(1, 1),   // oben rechts
    //         Pair(1, -1),  // unten rechts
    //         Pair(-1, 1),  // oben links
    //         Pair(-1, -1)  // unten links
    //     )
    //     return generateAllowedMoves(from, directions, 8)
    // }
    // bishop ist jetzt allmächtig und kann durch sachen fliegen. Stop on collision?
}