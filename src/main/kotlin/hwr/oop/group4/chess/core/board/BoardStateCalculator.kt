package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.opposite

class GameStateEvaluator(private val board: BoardView) {

  fun isCheck(playerColor: Color): Boolean {
    val opponentColor = playerColor.opposite()
    val kingLocation = findKing(playerColor) ?: return false
    return isFieldUnderAttack(kingLocation, opponentColor)
  }

  fun isCheckmate(playerColor: Color): Boolean {
    val kingLocation = findKing(playerColor) ?: return false
    if (!isCheck(playerColor)) return false
    if (canKingMoveAway(kingLocation)) return false
    if (canAnyPieceCapture(kingLocation, playerColor)) return false
    if (canAnyPieceBlock(kingLocation, playerColor)) return false
    return true
  }

  private fun findKing(color: Color): Location? {
    for (field in board) {
      val piece = field.getPiece()
      if (piece is King && piece.getColor() == color) {
        return field.location
      }
    }
    return null
  }

  private fun isFieldUnderAttack(target: Location, attackerColor: Color): Boolean {
    return getPiecesAttackingField(target, attackerColor).isNotEmpty()
  }

  private fun getPiecesAttackingField(
    target: Location,
    attackerColor: Color,
  ): Map<Piece, Location> {
    val attackers = mutableMapOf<Piece, Location>()
    for (field in board) {
      val piece = field.getPiece() ?: continue
      if (piece.getColor() == attackerColor) {
        val possibleMoves = piece.getPossibleLocationsToMove(field.location, board, true)
        if (target in possibleMoves) {
          attackers[piece] = field.location
        }
      }
    }
    return attackers
  }

  private fun canKingMoveAway(kingLocation: Location): Boolean {
    val king = board.getPiece(kingLocation) ?: return false
    val kingMoves = king.getPossibleLocationsToMove(kingLocation, board, false)
    return kingMoves.any { dest ->
      simulateMoveAndCheck(kingLocation, dest, king) { !isCheck(king.getColor()) }
    }
  }

  private fun canAnyPieceCapture(kingLocation: Location, playerColor: Color): Boolean {
    val checkingPieces = getPiecesAttackingField(kingLocation, playerColor.opposite())
    if (checkingPieces.size > 1) return false

    for ((attacker, attackerLoc) in checkingPieces) {
      for (field in board) {
        val piece = field.getPiece() ?: continue
        if (piece.getColor() != playerColor) continue
        val possible = piece.getPossibleLocationsToMove(field.location, board, true)
        if (attackerLoc in possible) {
          if (simulateMoveAndCheck(field.location, attackerLoc, piece) {
              !isCheck(playerColor)
            }) {
            return true
          }
        }
      }
    }

    return false
  }

  private fun canAnyPieceBlock(kingLocation: Location, playerColor: Color): Boolean {
    val checkingPieces = getPiecesAttackingField(kingLocation, playerColor.opposite())
    if (checkingPieces.size > 1) return false

    val (checkingPiece, attackerLocation) = checkingPieces.entries.first()
    if (checkingPiece is Knight || checkingPiece is Pawn) return false

    val targetSquares =
      checkingPiece.getPossibleLocationsToMove(attackerLocation, board, false)

    for (target in targetSquares) {
      for (field in board) {
        val piece = field.getPiece() ?: continue
        if (piece.getColor() != playerColor) continue
        if (target !in piece.getPossibleLocationsToMove(field.location, board, false)) continue

        if (simulateMoveAndCheck(field.location, target, piece) {
            !isCheck(playerColor)
          }) {
          return true
        }
      }
    }

    return false
  }

  private fun simulateMoveAndCheck(
    from: Location,
    to: Location,
    piece: Piece,
    condition: () -> Boolean
  ): Boolean {
    val originalTarget = board.getPiece(to)
    val fieldFrom = board.getField(from)
    val fieldTo = board.getField(to)

    fieldFrom.placePiece(null)
    fieldTo.placePiece(piece)

    val result = condition()

    fieldTo.placePiece(originalTarget)
    fieldFrom.placePiece(piece)

    return result
  }
}