package group4.chess.pieces

import group4.chess.board.Location

class Rook(color: Color): Piece(5, color) {
    override val name = "Rook"

    override fun allowedMoves(from: Location): List<Location> {
        val possibleMoves = mutableListOf<Location>()
        var possibleMovex :Char
        var possibleMovey :Int

        val directions = listOf(
            Pair(1, 0),   // rechts
            Pair(-1, 0),  // links
            Pair(0, 1),   // oben
            Pair(0, -1),  // unten
        )
        for ((x, y) in directions) {
            for (i in 1..8){
                possibleMovex = (from.x.code + x * i).toChar() // nur weils ein char ist, sonst possibleMovex = from.x + x * i
                possibleMovey = from.y + y * i

                if (possibleMovey in 1..8 && possibleMovex in 'a'..'h') {
                    possibleMoves.add(Location(possibleMovex, possibleMovey))
                }
            }
        } // kann man die code zeilen irgendwie zusammenfassen? viele sachen sind ähnlich

        return possibleMoves
    }
}