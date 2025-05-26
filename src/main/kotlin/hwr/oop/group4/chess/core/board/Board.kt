package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.fen.LoaderFEN
import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
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

    for (rank in Rank.values()) {
      val currentRankStart = generateRank(rank, previousRankStart, allFields)
      if (rank == Rank.ONE) {
        firstRankStart = currentRankStart
      }
      previousRankStart = currentRankStart
    }

    fields = allFields.toMap()
    return firstRankStart //A1
  }

  private fun generateRank(
    rank: Rank,
    previousRankStart: Field?,
    allFields: MutableMap<Location, Field>
  ): Field {
    var currentField = Field(Location(File.A, rank)) // first Field of Rank + moving pointer to the current Field
    val rankStart = currentField // stationary pointer to first Field of Rank
    var bottomField = previousRankStart // moving pointer to the Field below the current Field
    allFields[currentField.location] = currentField

    for (file in File.values().drop(1)) {
      val hasBottomField: Boolean = bottomField != null
      val newField = Field(Location(file, rank))
      currentField.connectRight(newField)
      newField.connectLeft(currentField)
      currentField = newField
      allFields[currentField.location] = currentField

      if (hasBottomField) {
        bottomField = bottomField!!.right
        currentField.connectBottom(bottomField!!)
        bottomField.connectTop(currentField)
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
    val nextFile = location.file.next()
    val nextRank = if (nextFile == null) location.rank.previous() else location.rank

    // If no next file and no previous rank return current field (on H1)
    if (nextFile == null && nextRank == null) {
      return getField(location)
    }

    val finalFile = nextFile ?: File.A
    val finalRank = nextRank ?: location.rank

    val nextLocation = Location(finalFile, finalRank)
    return fields[nextLocation] ?: getField(location)
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