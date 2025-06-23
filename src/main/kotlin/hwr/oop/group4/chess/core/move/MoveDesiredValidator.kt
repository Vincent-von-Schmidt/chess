package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.board.InvalidMoveException
import hwr.oop.group4.chess.core.board.InvalidPromotionException
import hwr.oop.group4.chess.core.board.NoPieceException
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Color

object MoveDesiredValidator {

  fun validateMove(
    board: BoardView,
    moveDesired: MoveDesired,
    playerAtTurnColor: Color,
    promoteToPiece: Piece? = null,
  ): MoveValidated {
    val movingPiece =
      board.getPiece(moveDesired.startLocation) ?: throw NoPieceException(
        moveDesired.startLocation
      )
    val occupyingPiece = board.getPiece(moveDesired.endLocation)

    validateMoveColor(movingPiece, playerAtTurnColor)
    validateMoveRules(moveDesired, movingPiece, occupyingPiece, board)
    val toPlacePiece = validatePromotion(
      moveDesired,
      movingPiece,
      promoteToPiece,
      playerAtTurnColor
    ) ?: movingPiece

    return MoveValidated(
      startLocation = moveDesired.startLocation,
      endLocation = moveDesired.endLocation,
      pieceCaptured = occupyingPiece,
      toPlacePiece = toPlacePiece
    )
  }

  private fun validateMoveColor(movingPiece: Piece, playerAtTurnColor: Color) {
    if (movingPiece.getColor() != playerAtTurnColor) throw InvalidMoveException(
      movingPiece
    )
  }

  private fun validateMoveRules(
    moveDesired: MoveDesired,
    movingPiece: Piece,
    occupyingPiece: Piece?,
    board: BoardView,
  ) {
    val isCapture = isCapture(moveDesired, movingPiece, occupyingPiece)
    if (isCapture && occupyingPiece is King) {
      throw InvalidMoveException(movingPiece, moveDesired.endLocation)
    }

    // TODO king cant move towards kind too close

    val legalDestinations = movingPiece.getPossibleLocationsToMove(
      moveDesired.startLocation,
      board,
      isCapture
    )
    if (moveDesired.endLocation !in legalDestinations) {
      throw InvalidMoveException(
        movingPiece,
        moveDesired.endLocation
      )
    }
  }

  private fun validatePromotion(
    moveDesired: MoveDesired,
    movingPiece: Piece,
    promoteToPiece: Piece?,
    playerAtTurnColor: Color,
  ): Piece? {
    val isPromotion =
      movingPiece is Pawn && (moveDesired.endLocation.rank == Rank.EIGHT || moveDesired.endLocation.rank == Rank.ONE)

    if (isPromotion) {
      if (promoteToPiece == null) {
        throw InvalidPromotionException(movingPiece)
      }
      if (promoteToPiece.getColor() != playerAtTurnColor) {
        throw InvalidPromotionException(movingPiece, promoteToPiece)
      }
      val checkedPromoteToPiece: Piece =
        when (promoteToPiece) {
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

  private fun isCapture(
    moveDesired: MoveDesired,
    movingPiece: Piece,
    occupyingPiece: Piece?,
  ): Boolean {

    if (occupyingPiece == null) {
      return false
    }
    if (occupyingPiece.getColor() == movingPiece.getColor()) {
      throw InvalidMoveException(
        movingPiece,
        moveDesired.endLocation
      )
    }
    return true
  }
}