package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.fen.LoaderFEN
import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.*
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.player.Turn
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.opposite

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

  fun setPieceToField(location: Location, piece: Piece) {
    getField(location).piece = piece
  }

  fun movePiece(move: Move, playerAtTurnColor: Color, promoteToPiece: Piece? = null,) {
    validateMove(move)
    validatePromotion(move)
    validateTurn(move, playerAtTurnColor)
    setPieceToField(move.endLocation, getPiece(move.startLocation)!!)
    removePieceFromField(move.startLocation)
  }

  private fun validateMove(move: Move) {

    val movingPiece: Piece? = getPiece(move.startLocation)
    val occupyingPiece = getPiece(move.endLocation)
    val piece = movingPiece?.description
    var capture = false

    if (movingPiece == null) throw NonExistentPieceException(move.startLocation.description)

    if (occupyingPiece != null) {
      if (occupyingPiece.color == movingPiece.color) {
        throw SameColorCaptureException(move.endLocation.description, occupyingPiece)
      }
      capture = isCapture(move, movingPiece)
    }

    if (move.endLocation !in movingPiece.allowedLocations(
        move.startLocation,
        this,
        capture
      )
    ) {
      throw IllegalMoveException(piece!!, move.endLocation.description)
    }
  }

//  fun isCheck(game: Game): Boolean {
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

  fun isCheckMate(){

  }

//  move out of the way (though he cannot castle!)
//  block the check with another piece or
//  capture the piece threatening the king.
//  else: checkmate >:)

  // TODO check if capturing king, and make exception

  fun validatePromotion(move: Move): Boolean {
    val toPromotePiece = getPiece(move.startLocation)!! //TODO give movingPiece
    val validPromotion =  ( (toPromotePiece is Pawn) && ((move.endLocation.rank == Rank.EIGHT)  || (move.endLocation.rank == Rank.ONE)) )
    if (validPromotion) {
      return true
    } else { NonPromotablePieceException(toPromotePiece)
      return false
    }
  }

  fun validateTurn(move: Move, color: Color) {
    val movingPiece: Piece = getPiece(move.startLocation)
      ?: throw NonExistentPieceException(move.startLocation.description)
    if (movingPiece.color != color) throw WrongColorMovedException(
      movingPiece
    )
  }

  private fun isCapture(move: Move, movingPiece: Piece): Boolean {
    return move.endLocation in movingPiece.allowedLocations(
      move.startLocation,
      this,
      true
    )
  }
}

class NoFieldException(location: Location) : Exception(
  "No field at ${location.description}"
)