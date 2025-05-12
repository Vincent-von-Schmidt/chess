package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.Location

class Knight(color: Color): Piece {
    override val name = "Knight"
    override val value = 3
    override val color = color

    override fun allowedMoves(from: Location): List<Location> {
        val possibleMoves = mutableListOf<Location>()
        var newX :Char
        var newY :Int

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
            newX = (from.x.code + x).toChar()
            newY = from.y + y
            if (newY in 1..8 && newX in 'a'..'h') {
                possibleMoves.add(Location(newX, newY))
            }
        }
        return possibleMoves
    }
}