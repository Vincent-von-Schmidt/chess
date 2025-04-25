package group4.chess.pieces

import group4.chess.board.Location

class King(color: Color): Piece(10, color) {
    override val name = "King"

    override fun allowedMoves(from: Location): List<Location> {
        val possibleMoves = mutableListOf<Location>()
        var possibleMovex :Char
        var possibleMovey :Int

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

        for ((x, y) in directions) {
            possibleMovex = from.x + x
            possibleMovey = from.y + y
            if (possibleMovey in 1..8 && possibleMovex in 'a'..'h') {
                possibleMoves.add(Location(possibleMovex, possibleMovey))
            }
        }
        return possibleMoves
    }
}