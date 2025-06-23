package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.move.MoveDesiredValidator.validateMove
import hwr.oop.group4.chess.core.move.MoveResult
import hwr.oop.group4.chess.core.pieces.King
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

  private fun isCheck(playerColor: Color): Boolean {
      val opponentColor = playerColor.opposite()
      val kingLocation = findKing(playerColor) ?: return false // Skip check validation if kings aren't on board
      return isSquareUnderAttack(kingLocation, opponentColor)
  }

  private fun isSquareUnderAttack(
    target: Location,
    attackerColor: Color,
  ): Boolean {
    return getCheckingPieces(target, attackerColor).isNotEmpty()
  }

  private fun getCheckingPieces(target: Location, attackerColor: Color): List<Piece> {
    val attackers = mutableListOf<Piece>()

    for ((location, field) in fields) {
      val piece = field.getPiece() ?: continue
      if (piece.getColor() == attackerColor) {
        val possibleMoves = piece.getPossibleLocationsToMove(location, this, true)
        if (target in possibleMoves) {
          attackers.add(piece)
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
    if (canAnyPieceBlock(kingLocation, checkingPiece, playerColor)) return false
    return true
  }

  private fun canKingMoveAway(kingLocation: Location) : Boolean { // TODO single Tests
    val king = getPiece(kingLocation) ?: return false
    val kingMoves = king.getPossibleLocationsToMove(kingLocation, this, false)
    return kingMoves.any { move ->
      !isCheck(king.getColor())
    }
  }

  private fun canAnyPieceCapture(kingLocation: Location, playerColor: Color): Boolean {
    val checkingPieces = getCheckingPieces(kingLocation, playerColor)
    if (checkingPieces.size > 1) return false // Double check can't be resolved by capturing

//    return checkingPieces.any { checkingPiece ->
//      fields.values.any { field ->
//        val piece = field.getPiece()
//        piece != null && piece.getColor() == playerColor &&
//            piece.getPossibleLocationsToMove(field.location, this, true)
//              .contains(checkingPiece.location)
//      }
//    }

    // Pseudocode
//    for (checkingPiece in checkingPieces) {
//      for (field in board) {
//        val piece = field.getPiece()
//        if (piece is mine && piece can capture checkingPiece) {
//          return true
//        }
//      }
//    }
//    return false
//  }
  }

  private fun canAnyPieceBlock(kingLocation: Location, attacker: Piece, playerColor: Color): Boolean {

//    val path = calculateAttackPath(kingLocation, attacker.location)
//    return path.any { blockSquare ->
//      fields.values.any { field ->
//        val piece = field.getPiece()
//        piece != null && piece.getColor() == playerColor &&
//            piece.getPossibleLocationsToMove(field.location, this, false)
//              .contains(blockSquare)
//      }
//    }
  }

//  FUNKTION canAnyPieceBlock(kingLocation, attacker, playerColor):
//  // 1. Berechne den Angriffsweg zwischen König und angreifender Figur
//  path = calculateAttackPath(kingLocation, attacker.location)
//
//  // 2. Für jedes Feld auf dem Angriffsweg:
//  FÜR JEDES blockSquare IN path:
//  // 3. Durchsuche alle Felder des Bretts
//  FÜR JEDES field IM Spielfeld:
//  piece = Feld.getPiece()
//
//  // 4. Prüfe ob eine eigene Figur das blockSquare erreichen kann
//  WENN piece != null UND piece.color == playerColor:
//  possibleMoves = piece.getPossibleMoves(field.location, capture=false)
//  WENN blockSquare IN possibleMoves:
//  RÜCKGABE true  // Blockade möglich!
//
//  // 5. Falls keine Figur blockieren kann
//  RÜCKGABE false
}