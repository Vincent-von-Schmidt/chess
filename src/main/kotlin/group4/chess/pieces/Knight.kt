package group4.chess.pieces

import group4.chess.board.Location

class Knight(color: Color): Piece(3, color) {
    override val name = "Knight"

    override fun allowedMoves(from: Location): List<Location> {
        val possibleMoves = mutableListOf<Location>()
        var possibleMovex :Char
        var possibleMovey :Int

        val directions = listOf(
            Pair(-2, 1),   // oben 1 links
            Pair(-1, 2),  // oben 2 links
            Pair(2, 1),  // oben 1 rechts
            Pair(1, 2),  // oben 2 rechts
            Pair(-2, -1),   // unten 1 links
            Pair(-1, -2),  // unten 2 links
            Pair(2, -1),  // unten 1 rechts
            Pair(1, -2)  // unten 2 rechts
        )

        for ((x, y) in directions) {
            possibleMovex = (from.x.code + x).toChar()
            possibleMovey = from.y + y
            if (possibleMovey in 1..8 && possibleMovex in 'a'..'h') {
                possibleMoves.add(Location(possibleMovex, possibleMovey))
            }
        }
        return possibleMoves
    }
}