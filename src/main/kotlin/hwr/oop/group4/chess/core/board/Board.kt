package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.*
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

    for (rank in Rank.values()) {
      var previousFile: File? = null
      for (file in File.values()) {
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
    return getField(location).piece
  }

  private fun findKing(color: Color): Location {
    val king = King(color)
    for ((location, field) in fields) {
      val piece = field.piece
      if (piece is King && piece.color == color) {
        return location
      }
    }
    throw NoPieceException(piece = king)
  }

  private fun removePieceFromField(location: Location) {
    getField(location).piece = null
  }

  private fun placePieceToField(location: Location, piece: Piece) {
    getField(location).piece = piece
  }

  fun movePiece(
    move: Move,
    playerAtTurnColor: Color,
    promoteToPiece: Piece? = null,
  ) {
    isCheck(playerAtTurnColor)
    val movingPiece: Piece? = getPiece(move.startLocation)
    val occupyingPiece: Piece? = getPiece(move.endLocation)

    if (movingPiece == null) throw NoPieceException(move.startLocation)

    validateMove(move, movingPiece, occupyingPiece)
    validateTurn(movingPiece, playerAtTurnColor)
    val promotionPiece =
      validatePromotion(move, movingPiece, promoteToPiece, playerAtTurnColor)

    val pieceToPlace = promotionPiece ?: movingPiece
    placePieceToField(move.endLocation, pieceToPlace)
    removePieceFromField(move.startLocation)
  }

  private fun validateMove(
    move: Move,
    movingPiece: Piece,
    occupyingPiece: Piece?,
  ) {

    val isCapture = isCapture(move, movingPiece, occupyingPiece)
    val legalDestinations =
      movingPiece.availableLocationsToMove(move.startLocation, this, isCapture)

    if (move.endLocation !in legalDestinations) {
      throw InvalidMoveException(
        movingPiece,
        move.endLocation
      )
    }
  }

  private fun isCheck(playerAtTurnColor: Color): Boolean {
    val opponentColor = playerAtTurnColor.opposite()
    val kingLocation = findKing(opponentColor)
    return isSquareUnderAttack(kingLocation, playerAtTurnColor)
  }

  private fun isSquareUnderAttack(target: Location, attackerColor: Color): Boolean {
    for ((location) in fields) {
      val piece = getPiece(location) ?: continue
      if (piece.color == attackerColor) {
        val possibleMoves = piece.availableLocationsToMove(location, this, true)
        if (target in possibleMoves) {
          return true
        }
      }
    }
    return false
  }

  // TODO check if capturing king, and make exception

  private fun validatePromotion(
    move: Move,
    movingPiece: Piece,
    promoteToPiece: Piece?,
    playerAtTurnColor: Color,
  ): Piece? {
    val isPromotion =
      movingPiece is Pawn && (move.endLocation.rank == Rank.EIGHT || move.endLocation.rank == Rank.ONE)

    if (isPromotion) {
      if (promoteToPiece == null) {
        throw InvalidPromotionException(movingPiece)
      }
      if (promoteToPiece.color != playerAtTurnColor) {
        throw InvalidPromotionException(movingPiece, promoteToPiece)
      }
      val checkedPromoteToPiece: Piece = when (promoteToPiece) { // TODO this into parser?
        is Queen -> Queen(playerAtTurnColor)
        is Rook -> Rook(playerAtTurnColor)
        is Bishop -> Bishop(playerAtTurnColor)
        is Knight -> Knight(playerAtTurnColor)
        else -> throw InvalidPromotionException(movingPiece, promoteToPiece)
      }
      return checkedPromoteToPiece
    }
    return null
  }

  private fun validateTurn(movingPiece: Piece, playerAtTurnColor: Color) {
    if (movingPiece.color != playerAtTurnColor) throw InvalidMoveException(movingPiece)
  }

  private fun isCapture(
    move: Move,
    movingPiece: Piece,
    occupyingPiece: Piece?,
  ): Boolean {

    if (occupyingPiece == null) {
      return false
    }
    if (occupyingPiece.color == movingPiece.color) {
      throw InvalidMoveException(
        movingPiece,
        move.endLocation
      )
    }
    return true
  }
}