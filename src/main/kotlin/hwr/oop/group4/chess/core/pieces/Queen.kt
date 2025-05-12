package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Location

class Queen(color: Color): Piece {
    override val name = "Queen"
    override val value = 9
    override val color = color

    override fun allowedMoves(from: Location): List<Location> {

        val directions = listOf(
            Pair(1, 0),   // rechts
            Pair(-1, 0),  // links
            Pair(0, 1),   // oben
            Pair(0, -1),  // unten
            Pair(1, 1),   // oben rechts
            Pair(1, -1),  // unten rechts
            Pair(-1, 1),  // oben links
            Pair(-1, -1)  // unten links
        )
        return generateAllowedMoves(from, directions, 8)
    }
}