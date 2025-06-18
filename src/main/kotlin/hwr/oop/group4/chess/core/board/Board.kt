package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.move.MoveDesiredValidator.validateMove
import hwr.oop.group4.chess.core.move.MoveResult
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.opposite

class Board(piecePlacementMap: Map<Location, Piece>) : BoardView {

  init {
    generateBoard()
    this.initializeWithPieces(piecePlacementMap)
  }

  private lateinit var fields: Map<Location, Field>

  private fun generateBoard(): Map<Location, Field> {
    val allFields = mutableMapOf<Location, Field>()
    var previousRank: Rank? = null

    for (rank in Rank.entries) {
      var previousFile: File? = null
      for (file in File.entries) {
        val location = Location(file, rank)
        val field = Field(location)
        allFields[location] = field

        if (previousFile != null) {              // Set left /right neighbor
          val leftLocation = Location(previousFile, rank)
          val leftField = allFields[leftLocation]!!

          field.connectLeft(leftField)
          leftField.connectRight(field)
        }

        if (previousRank != null) {              // Set bottom /top neighbor
          val bottomLocation = Location(file, previousRank)
          val bottomField = allFields[bottomLocation]!!
          field.connectBottom(bottomField)
          bottomField.connectTop(field)
        }

        previousFile = file
      }
      previousRank = rank
    }
    fields = allFields.toMap()
    return fields
  }

  private fun initializeWithPieces(pieces: Map<Location, Piece>) {
    for ((location, piece) in pieces) {
      val field = getField(location)
      placePieceToField(field.location, piece)
    }
  }

  override fun getField(location: Location): Field {
    return fields[location]!!
  }

  override fun getPiece(location: Location): Piece? {
    return getField(location).getPiece()
  }

  private fun findKing(color: Color): Location {
    val king = King(color)
    for ((location, field) in fields) {
      val piece = field.getPiece()
      if (piece is King && piece.getColor() == color) {
        return location
      }
    }
    throw NoPieceException(piece = king)
  }

  private fun removePieceFromField(location: Location) {
    getField(location).placePiece(null)
  }

  private fun placePieceToField(location: Location, piece: Piece) {
    getField(location).placePiece(piece)
  }

  fun movePiece(
    moveDesired: MoveDesired,
    playerAtTurnColor: Color,
    promoteToPiece: Piece? = null,
  ): MoveResult {

    validateMove(this, moveDesired, playerAtTurnColor, promoteToPiece)
    val validatedMove =
      validateMove(this, moveDesired, playerAtTurnColor, promoteToPiece)

    // temporary move (Check-test)
    placePieceToField(validatedMove.endLocation, validatedMove.toPlacePiece)
    removePieceFromField(validatedMove.startLocation)

    if (isCheck(playerAtTurnColor)) {
      // undo Move
      removePieceFromField(validatedMove.endLocation)
      placePieceToField(validatedMove.startLocation, validatedMove.toPlacePiece)
      throw CheckException(playerAtTurnColor)
    }

    val opponentInCheck = isCheck(playerAtTurnColor.opposite())

    val isCheckmate =
      opponentInCheck && isCheckmate(playerAtTurnColor.opposite())

    return MoveResult(
      move = validatedMove,
      opponentInCheck = opponentInCheck,
      isCheckmate = isCheckmate
    )
  }

  private fun isCheck(color: Color): Boolean {
    try {
      val opponentColor = color.opposite()
      val kingLocation = findKing(color)
      return isSquareUnderAttack(kingLocation, opponentColor)
    } catch (e: NoPieceException) {
      return false // Skip check validation if kings aren't on board
    }
  }

  private fun isSquareUnderAttack(
    target: Location,
    attackerColor: Color,
  ): Boolean {
    for ((location) in fields) {
      val piece = getPiece(location) ?: continue
      if (piece.getColor() == attackerColor) {
        val possibleMoves =
          piece.getPossibleLocationsToMove(location, this, true)
        if (target in possibleMoves) {
          return true
        }
      }
    }
    return false
  }

  private fun isCheckmate(playerAtTurnColor: Color): Boolean {
    return false
  }
}