package group4.chess.board
import group4.chess.pieces.Piece

class Board {
    // piece kann null sein, da bei der Initierung des Bords, keine uebergeben werden

    private val fields: Map<Location, Field> = run { // mapt locations zu field
        val allFields = mutableMapOf<Location, Field>()
        for (x in 'a'..'h') {
            for (y in 1..8) {
                val loc = Location(x, y)
                allFields[loc] = Field(loc)
            }
        }
        allFields.toMap()
    }
    fun getAllFields(): Map<Location, Field> = fields

    fun getField(location: Location): Field {
        return fields[location] ?: throw IllegalArgumentException("Ungültige Location: $location")
    }

    fun removePieceFromField(location: Location) {
        fields[location]?.piece = null
    }

    fun setPieceToField(location: Location, piece: Piece) {
        fields[location]?.piece = piece
    }
}