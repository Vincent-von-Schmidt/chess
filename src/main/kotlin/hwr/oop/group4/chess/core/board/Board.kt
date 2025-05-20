package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Piece

class Board {
  private val root: Field = generateFirstFieldOfRank() // ist A1
  private lateinit var fields: Map<Location, Field>

  private fun generateFirstFieldOfRank(): Field {
    var previousRankStart: Field? = null
    lateinit var firstRankStart: Field
    val allFields = mutableMapOf<Location, Field>()

    for (currentRank in 1..8) {
      val currentRankStart =
        generateRank(currentRank, previousRankStart, allFields)
      if (currentRank == 1) {
        firstRankStart = currentRankStart
      }
      previousRankStart = currentRankStart
    }
    fields = allFields.toMap()
    return firstRankStart //A1
  }

  private fun generateRank(
    rank: Int,
    previousRankStart: Field?,
    allFields: MutableMap<Location, Field>,
  ): Field {
    var currentField = Field(
      Location(
        File.values()[0],
        rank
      )
    ) // first Field of Rank + moving pointer to the current Field
    val rankStart: Field =
      currentField // stationary pointer to first Field of Rank
    var bottomField: Field? =
      previousRankStart // moving pointer to the Field below the current Field
    allFields[currentField.location] = currentField


    for (file in 1..8) {
      val hasBottomField: Boolean = bottomField != null
      val isNotLastFile: Boolean = file < 8

      if (hasBottomField) {
        currentField.bottom = bottomField
        bottomField!!.top = currentField
      }

      if (isNotLastFile) { // new field on the right of current
        val newField = Field(Location(File.values()[file], rank))
        currentField.right = newField
        newField.left = currentField
        currentField = newField
        allFields[currentField.location] = currentField
      }

      if (hasBottomField && isNotLastFile) {
        bottomField = bottomField!!.right!!
      }
    }
    return rankStart
  }

  fun getField(location: Location): Field {
    return fields[location]
      ?: throw NoFieldException(location)
  }

  fun getPiece(location: Location): Piece? {
    return getField(location).piece
  }

  fun nextField(location: Location): Field { // next means go one right if possible, else switch rank 1 down
    val fileIndex = location.file.ordinal
    val rank = location.rank

    return if (fileIndex < File.values().lastIndex) {
      getField(
        Location(
          File.values()[fileIndex + 1],
          rank
        )
      )   // next file, same rank
    } else if (rank > 1) {
      getField(
        Location(
          File.A,
          rank - 1
        )
      )  // beginning of the next rank, down
    } else {
      getField(location) // last Field with no successor (H1) returns H1
    }
  }

  private fun removePieceFromField(location: Location) {
    getField(location).piece = null
  }

  fun setPieceToField(location: Location, piece: Piece) {
    getField(location).piece = piece
  }

  fun movePiece(move: Move) {
    setPieceToField(move.endLocation, getPiece(move.startLocation)!!)
    removePieceFromField(move.startLocation)
  }
}

class NoFieldException(location: Location) : Exception(
  "No field at ${location.description}"
)
