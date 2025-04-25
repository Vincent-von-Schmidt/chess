package group4.chess.pieces

import group4.chess.board.Location

class Pawn(color: Color): Piece(1, color) {
    override val name = "Pawn"

    override fun allowedMoves(from: Location): List<Location> {

        val direction = if (color == Color.WHITE) 1 else -1

        val possibleMoves = mutableListOf<Location>()
        var possibleMovey :Int = from.y + direction
        if (possibleMovey in 1..8){
            possibleMoves.add(Location(from.x, possibleMovey))
        }

        return possibleMoves
    }   // schlagen muss noch implementiert werden
}