package group4.chess.pieces

import group4.chess.board.Location

class Queen(color: Color): Piece(9, color) {
    override val name = "Queen"

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