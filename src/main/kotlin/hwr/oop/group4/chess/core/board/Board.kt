package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.fen.LoaderFEN
import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION

class Board(fen: String = STARTING_POSITION) {
  private val root: Field

  init {
    root = generateFirstFieldOfRank()
    val piecePlacement = ReaderFEN(fen).piecePlacement
    LoaderFEN.placePieces(piecePlacement, this)
  }

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
        currentField.connectBottom(bottomField!!)
        bottomField.connectTop(currentField)
      }

      if (isNotLastFile) { // new field on the right of current
        val newField = Field(Location(File.values()[file], rank))
        currentField.connectRight(newField)
        newField.connectLeft(currentField)
        currentField = newField
        allFields[currentField.location] = currentField
      }

      if (hasBottomField && isNotLastFile) {
        bottomField = bottomField?.right!!
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

  fun nextField(location: Location): Field {
    val nextFileIndex = location.file.ordinal + 1
    val nextRank = if (nextFileIndex > File.values().lastIndex) location.rank - 1 else location.rank
    val nextFile = if (nextFileIndex > File.values().lastIndex) File.A else File.values()[nextFileIndex]

    val nextLocation = Location(nextFile, nextRank)

    return fields[nextLocation] ?: getField(location) // returns H1 at H1
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