package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.*
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Color

class Board : BoardView {

  init {
    generateBoard()
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

        if (file == File.A && rank == Rank.ONE) {
          firstField = field
        }

        previousFile = file
      }
      previousRank = rank
    }

    fields = allFields.toMap()
    return firstField //TODO i need?
      ?: throw IllegalStateException("No starting field found")
  }

  fun initializeWithPieces(pieces: Map<Location, Piece>) {
    for ((location, piece) in pieces) {
      val field = getField(location)
      placePieceToField(field.location, piece)
    }
  }

  override fun getField(location: Location): Field {
    return fields[location]
      ?: throw NoFieldException(location)
  }

  override fun getPiece(location: Location): Piece? {
    return getField(location).piece
  }

  private fun findKing(color: Color): Location? {
    for ((location, field) in fields) {
      val piece = field.piece
      if (piece is King && piece.color == color) {
        return location
      }
    }
    return null
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
    val movingPiece: Piece? = getPiece(move.startLocation)
    val occupyingPiece: Piece? = getPiece(move.endLocation)

    validateMove(move, movingPiece, occupyingPiece)
    validateTurn(movingPiece!!, playerAtTurnColor)
    val promotionPiece =
      validatePromotion(move, movingPiece, promoteToPiece, playerAtTurnColor)


    val pieceToPlace = promotionPiece ?: movingPiece
    placePieceToField(move.endLocation, pieceToPlace)
    removePieceFromField(move.startLocation)
  }

  private fun validateMove(
    move: Move,
    movingPiece: Piece? = null,
    occupyingPiece: Piece? = null,
  ) {
    if (movingPiece == null) throw NonExistentPieceException(move.startLocation.description)

    val isCapture = isCapture(move, movingPiece, occupyingPiece)
    val legalDestinations =
      movingPiece.allowedLocations(move.startLocation, this, isCapture)

    if (move.endLocation !in legalDestinations) {
      throw IllegalMoveException(
        movingPiece.description,
        move.endLocation.description
      )
    }
  }

  // TODO implement desired Moves, user wanted moves are already here in movePiece
//  private fun isCheck(game: Game): Boolean {
//    val opponentColor = game.turn.colorToMove.opposite()
//    val kingLocation = game.board.findKing(opponentColor)
//      ?: throw Exception("No king found for $opponentColor")
//
//    // Überprüfen, ob eine gegnerische Figur das Königsfeld bedroht
//    for (location in game.board.allLocations()) {
//      val piece = game.board.getPiece(location) ?: continue
//      if (piece.color != opponentColor) {
//        val possibleMoves = piece.allowedLocations(location, game.board, true)
//        if (kingLocation in possibleMoves) {
//          return true
//        }
//      }
//    }
//
//    return false
//  }

  private fun isCheckMate() {

  }

//  move out of the way (though he cannot castle!)
//  block the check with another piece or
//  capture the piece threatening the king.
//  else: checkmate >:)

  // TODO check if capturing king, and make exception

  private fun validatePromotion(
    move: Move,
    movingPiece: Piece,
    promoteToPiece: Piece?,
    playerAtTurnColor: Color
  ): Piece? {
    val isPromotion =
      movingPiece is Pawn && (move.endLocation.rank == Rank.EIGHT || move.endLocation.rank == Rank.ONE)

    if (isPromotion) {
      if (promoteToPiece == null) {
        throw WrongPromoteChoiceException(movingPiece)
      }
      if (promoteToPiece.color != playerAtTurnColor) {
        throw WrongPromoteChoiceException(movingPiece, promoteToPiece)
      }
        val checkedPromoteToPiece: Piece = when (promoteToPiece) {
          is Queen -> Queen(playerAtTurnColor)
          is Rook -> Rook(playerAtTurnColor)
          is Bishop -> Bishop(playerAtTurnColor)
          is Knight -> Knight(playerAtTurnColor)
          else -> throw WrongPromoteChoiceException(movingPiece, promoteToPiece)
        }
      return checkedPromoteToPiece
    }
    return null
  }

  private fun validateTurn(movingPiece: Piece, playerAtTurnColor: Color) {
    if (movingPiece.color != playerAtTurnColor) throw WrongColorMovedException(
      movingPiece
    )
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
      throw SameColorCaptureException(
        move.endLocation.description,
        occupyingPiece
      )
    }
    return true
  }
}