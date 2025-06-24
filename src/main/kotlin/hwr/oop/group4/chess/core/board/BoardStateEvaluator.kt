package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.MoveValidated
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.pieces.Knight
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.opposite

class BoardStateEvaluator(private val board: BoardView) {

  fun isCheck(playerColor: Color): Boolean {
    val opponentColor = playerColor.opposite()
    val kingLocation = findKing(playerColor) ?: return false
    return isFieldUnderAttack(kingLocation, opponentColor)
  }

  fun testSelfCheck(validatedMove: MoveValidated, playerColor: Color) {
    val from = validatedMove.startLocation
    val to = validatedMove.endLocation
    val piece = validatedMove.toPlacePiece
    val causesCheck = board.simulateMoveAndCheck(from, to, piece) {
      isCheck(playerColor)
    }
    if (causesCheck) {
      throw CheckException(playerColor)
    }
  }

  fun isCheckmate(playerColor: Color): Boolean {
    val kingLocation = findKing(playerColor) ?: return false
    if (!isCheck(playerColor)) return false
    if (canKingMoveAway(kingLocation)) return false
    if (canAnyPieceCapture(kingLocation, playerColor)) return false
    if (canAnyPieceBlock(kingLocation, playerColor)) return false
    return true
  }

  fun findKing(color: Color): Location? {
    for (field in board) {
      val piece = field.getPiece()
      if (piece is King && piece.getColor() == color) {
        return field.location
      }
    }
    return null
  }

  private fun isFieldUnderAttack(
    target: Location,
    attackerColor: Color,
  ): Boolean {
    return getPiecesAttackingField(target, attackerColor).isNotEmpty()
  }

  private fun getPiecesAttackingField(
    target: Location,
    attackerColor: Color,
  ): Map<Piece, Location> {
    val attackers = mutableMapOf<Piece, Location>()

    for (field in board) {
      val piece = field.getPiece()
      if (piece == null || piece.getColor() != attackerColor) continue

      val possibleMoves =
        piece.getPossibleLocationsToMove(field.location, board, true)
      if (target in possibleMoves) {
        attackers[piece] = field.location
      }
    }

    return attackers
  }

  private fun canKingMoveAway(kingLocation: Location): Boolean {
    val king = board.getPiece(kingLocation) ?: return false
    return king
      .getPossibleLocationsToMove(kingLocation, board, capture = false)
      .any { endLocation ->
        board.simulateMoveAndCheck(kingLocation, endLocation, king) {
          !isCheck(king.getColor())
        }
      }
  }

  private fun canAnyPieceCapture(
    kingLocation: Location,
    playerColor: Color,
  ): Boolean {
    val attackers =
      getPiecesAttackingField(kingLocation, playerColor.opposite())
    if (attackers.size > 1) return false // double check can't be captured

    val attackerLocation = attackers.values.firstOrNull() ?: return false

    for (field in board) {
      val piece = field.getPiece() ?: continue
      if (piece.getColor() != playerColor) continue

      val captureMoves =
        piece.getPossibleLocationsToMove(field.location, board, true)
      if (attackerLocation !in captureMoves) continue

      val moveIsSafe =
        board.simulateMoveAndCheck(field.location, attackerLocation, piece) {
          !isCheck(playerColor)
        }

      if (moveIsSafe) return true
    }

    return false
  }

  private fun canAnyPieceBlock(
    kingLocation: Location,
    playerColor: Color,
  ): Boolean {
    val checkingPieces =
      getPiecesAttackingField(kingLocation, playerColor.opposite())
    if (checkingPieces.size > 1) return false

    val (attacker, attackerLocation) = checkingPieces.entries.first()
    if (attacker is Knight || attacker is Pawn) return false

    val toBlockFields = attacker.getPossibleLocationsToMove(
      attackerLocation,
      board,
      capture = false
    )

    for (toBlockField in toBlockFields) {
      for (field in board) {
        val piece = field.getPiece() ?: continue
        if (piece.getColor() != playerColor) continue

        val possibleMoves = piece.getPossibleLocationsToMove(
          field.location,
          board,
          capture = false
        )
        if (toBlockField !in possibleMoves) continue

        val moveIsSafe =
          board.simulateMoveAndCheck(field.location, toBlockField, piece) {
            !isCheck(playerColor)
          }

        if (moveIsSafe) return true
      }
    }

    return false
  }
}