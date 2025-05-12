package group4.chess.board
import group4.chess.move.Move
import group4.chess.pieces.Piece

class Board {
    // piece kann null sein, da bei der Initierung des Bords, keine uebergeben werden
    private val fields: Map<Location, Field>

    init {
        fields = generateFields()
    }

    private fun generateFields(): Map<Location, Field> {
        val allFields = mutableMapOf<Location, Field>()
        for (x in 'a'..'h') {
            for (y in 1..8) {
                val loc = Location(x, y)
                allFields[loc] = Field(loc)
            }
        }
        allFields.toMap()

        return allFields
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

    fun movePiece(move: Move) {
        val startField = move.startLocation
        val endField = move.endLocation
        val toMovePiece = move.toMovePiece
        val pieceDescription = "${toMovePiece.color} ${toMovePiece.name}"

        if (getField(startField).piece != toMovePiece) {
            throw IllegalArgumentException("$startField does not contain a $pieceDescription")
        }

        if (endField !in toMovePiece.allowedMoves(startField)) {
            throw IllegalArgumentException("$pieceDescription can not be moved to $endField")
        }

        setPieceToField(endField, toMovePiece)
        removePieceFromField(startField)
    }
    /*
  val last_turn: Move = Move()

  fun validate(): Boolean {

       // hier ob das schach oder mat ändert
      return true
  }
*/
}