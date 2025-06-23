package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.move.MoveDesiredValidator.validateMove
import hwr.oop.group4.chess.core.move.MoveResult
import hwr.oop.group4.chess.core.move.MoveValidated
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.pieces.Knight
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.opposite

class Board(piecePlacementMap: Map<Location, Piece>) : BoardView {

  private val fields: Map<Location, Field> = generateBoard()

  init {
    this.initializeWithPieces(piecePlacementMap)
  }

  private fun generateBoard(): Map<Location, Field> {
    val fieldMap = mutableMapOf<Location, Field>()

    // create fields with lazy providers
    Rank.entries.forEach { rank ->
      File.entries.forEach { file ->
        val loc = Location(file, rank)
        fieldMap[loc] = Field(loc) {
          mapOf(
            Direction.TOP to fieldMap[rank.next()?.let { Location(file, it) }],
            Direction.BOTTOM to fieldMap[rank.previous()?.let { Location(file, it) }],
            Direction.LEFT to fieldMap[file.previous()?.let { Location(it, rank) }],
            Direction.RIGHT to fieldMap[file.next()?.let { Location(it, rank) }]
          )
        }
      }
    }
    return fieldMap.toMap()
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

  private fun findKing(color: Color): Location? {
    for ((location, field) in fields) {
      val piece = field.getPiece()
      if (piece is King && piece.getColor() == color) {
        return location
      }
    }
    return null
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

    val validatedMove =
      validateMove(this, moveDesired, playerAtTurnColor, promoteToPiece)

    // temporary move
    placePieceToField(validatedMove.endLocation, validatedMove.toPlacePiece)
    removePieceFromField(validatedMove.startLocation)
    testSelfCheck(validatedMove, playerAtTurnColor)
    val opponentInCheck = isCheck(playerAtTurnColor.opposite())

    val isCheckmate =
      opponentInCheck && isCheckmate(playerAtTurnColor.opposite())

    return MoveResult(
      move = validatedMove,
      opponentInCheck = opponentInCheck,
      isCheckmate = isCheckmate
    )
  }

  private fun isCheck(playerColor: Color): Boolean {
    val opponentColor = playerColor.opposite()
    val kingLocation = findKing(playerColor)
      ?: return false // Skip check validation if kings aren't on board
    return isFieldUnderAttack(kingLocation, opponentColor)
  }

  private fun testSelfCheck(validatedMove: MoveValidated, playerAtTurnColor: Color) {
    if (isCheck(playerAtTurnColor)) {
      // undo Move
      removePieceFromField(validatedMove.endLocation)
      placePieceToField(validatedMove.startLocation, validatedMove.toPlacePiece)
      throw CheckException(playerAtTurnColor)
    }
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

    for ((location, field) in fields) {
      val piece = field.getPiece() ?: continue
      if (piece.getColor() == attackerColor) {
        val possibleMoves =
          piece.getPossibleLocationsToMove(location, this, true)
        if (target in possibleMoves) {
          attackers[piece] = location
        }
      }
    }
    return attackers
  }

  private fun isCheckmate(playerColor: Color): Boolean {
    val kingLocation = findKing(playerColor) ?: return false
    if (!isCheck(playerColor)) return false
    if (canKingMoveAway(kingLocation)) return false
    if (canAnyPieceCapture(kingLocation, playerColor)) return false
    if (canAnyPieceBlock(kingLocation, playerColor)) return false
    return true
  }

  private fun canKingMoveAway(kingLocation: Location): Boolean { // TODO single Tests
    val king = getPiece(kingLocation) ?: return false
    val kingMoves = king.getPossibleLocationsToMove(kingLocation, this, false)
    return kingMoves.any {
      !isCheck(king.getColor())
    }
  }

  private fun canAnyPieceCapture(
    kingLocation: Location,
    playerColor: Color,
  ): Boolean {
    val checkingPieces =
      getPiecesAttackingField(kingLocation, playerColor.opposite())
    if (checkingPieces.size > 1) return false // Double check can't be resolved by capturing

    for (checkingPiece in checkingPieces) {
      for ((location) in fields) {
        val piece = getPiece(location)
        if (piece != null && piece.getColor() == playerColor &&
          checkingPiece.value in piece.getPossibleLocationsToMove(location, this, true)) {
          return true
        }
      }
    }
    return false
  }

  private fun canAnyPieceBlock(
    kingLocation: Location,
    playerColor: Color,
  ): Boolean {

    val checkingPieces =
      getPiecesAttackingField(kingLocation, playerColor.opposite())
    if (checkingPieces.size > 1) return false // Double check can't be blocked

    val (checkingPiece, attackerLocation) = checkingPieces.entries.first()
    if (checkingPiece is Knight || checkingPiece is Pawn) return false // can’t block these

    val targetSquares =
      checkingPiece.getPossibleLocationsToMove(attackerLocation, this, false)
    val ownPieces =
      fields.filterValues { it.getPiece()?.getColor() == playerColor }

    for (target in targetSquares) {
      for ((location, field) in ownPieces) {
        val piece = field.getPiece() ?: continue
        if (target !in piece.getPossibleLocationsToMove(
            location,
            this,
            false
          )
        ) continue

        // simulate the move
        val originalPieceAtTarget = getPiece(target)
        removePieceFromField(location)
        placePieceToField(target, piece)

        val stillCheck = isCheck(playerColor)

        // undo the move
        removePieceFromField(target)
        placePieceToField(location, piece)
        if (originalPieceAtTarget != null) placePieceToField(
          target,
          originalPieceAtTarget
        )

        if (!stillCheck) return true
      }
    }

    return false
  }
}