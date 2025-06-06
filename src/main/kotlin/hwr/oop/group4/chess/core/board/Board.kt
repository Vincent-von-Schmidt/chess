package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.fen.InvalidPiecePlacementException
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
    root = generateBoard()
    val piecePlacement = ReaderFEN(fen).piecePlacement
    LoaderFEN.placePieces(piecePlacement, this)
  }

  private lateinit var fields: Map<Location, Field>

  private fun generateBoard(): Field {
    val allFields = mutableMapOf<Location, Field>()
    var firstField: Field? = null
    var previousRank: Rank? = null

    for (rank in Rank.values()) {
      var previousFile: File? = null
      for (file in File.values()) {
        val location = Location(file, rank)
        val field = Field(location)
        allFields[location] = field

        if (previousFile != null) {              // Set left /right neighbor
          val leftLocation = Location(previousFile, rank)
          val leftField = allFields[leftLocation] ?: throw NoFieldException(leftLocation)

          field.connectLeft(leftField)
          leftField.connectRight(field)
        }

        if (previousRank != null) {              // Set bottom /top neighbor
          val bottomLocation = Location(file, previousRank)
          val bottomField = allFields[bottomLocation] ?: throw NoFieldException(bottomLocation)
          field.connectBottom(bottomField)
          bottomField.connectTop(field)
        }

        if (file == File.A && rank == Rank.ONE) {
          firstField = field
        }

        previousFile = file
      }
      previousRank = rank
    }

    fields = allFields.toMap()
    return firstField ?: throw IllegalStateException("No starting field found")
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
    val nextRank =
      if (nextFile == null) location.rank.previous() else location.rank

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

  fun movePiece(move: Move, promoteToPiece: Piece? = null) {
    move.validateMove(this)
    val piece: Piece = getPiece(move.startLocation) ?: throw IllegalStateException()
    if (promoteToPiece is Piece && move.isPromotion()) {
      setPieceToField(
        move.endLocation,
        promoteToPiece
      )
    } else setPieceToField(move.endLocation, piece)
    removePieceFromField(move.startLocation)
  }
}