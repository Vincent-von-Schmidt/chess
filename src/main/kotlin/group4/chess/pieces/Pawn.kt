package group4.chess.pieces

import group4.chess.board.Location

class Pawn(color: Color): Piece {
    override val name = "Pawn"
    override val value = 1
    override val color = color

    override fun allowedMoves(from: Location): List<Location> {
        val direction = if (color == Color.WHITE) 1 else -1

        val possibleMoves = mutableListOf<Location>()
        val newY :Int = from.y + direction
        if (newY in 1..8){
            possibleMoves.add(Location(from.x, newY))
        }

        return possibleMoves
    }   // schlagen muss noch implementiert werden
}