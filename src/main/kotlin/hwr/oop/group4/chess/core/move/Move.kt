package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.utils.opposite

class Move(
  val startLocation: Location,
  val endLocation: Location,
) {
  private val startLoc = startLocation.description
  private val endLoc = endLocation.description

  fun validateMove(
    board: Board,
  ) {

    val movingPiece: Piece? = getMovingPiece(startLocation, board)
    val occupyingPiece = board.getPiece(endLocation)
    val piece = movingPiece?.description
    var capture = false

    if (movingPiece == null) throw NonExistentPieceException(startLoc)

    if (occupyingPiece != null) {
      if (occupyingPiece.color == movingPiece.color) {
        throw SameColorCaptureException(endLoc, occupyingPiece)
      }
      capture = isCapture(movingPiece, board)
    }

    if (endLocation !in movingPiece.allowedLocations(
        startLocation,
        board,
        capture
      )
    ) {
      throw IllegalMoveException(piece!!, endLoc)
    }
  }

  fun validateTurn(game: Game) {
    val movingPiece: Piece = getMovingPiece(startLocation, game.board)
      ?: throw NonExistentPieceException(startLoc)
    if (movingPiece.color != game.turn.colorToMove) throw WrongColorMovedException(
      movingPiece
    )
  }

  fun isCheck(game: Game): Boolean {
    val opponentColor = game.turn.colorToMove.opposite()
    val kingLocation = game.board.findKing(opponentColor)
      ?: throw Exception("No king found for $opponentColor")

    // Überprüfen, ob eine gegnerische Figur das Königsfeld bedroht
    for (location in game.board.allLocations()) {
      val piece = game.board.getPiece(location) ?: continue
      if (piece.color != opponentColor) {
        val possibleMoves = piece.allowedLocations(location, game.board, true)
        if (kingLocation in possibleMoves) {
          return true
        }
      }
    }

    return false
  }

  fun isCheckMate(){

  }

//  move out of the way (though he cannot castle!)
//  block the check with another piece or
//  capture the piece threatening the king.
//  else: checkmate >:)

  // TODO check if capturing king, and make exception

  fun validatePromotion(game: Game): Boolean {
    val toPromotePiece = getMovingPiece(startLocation, game.board)!!
    val validPromotion =  ( (toPromotePiece is Pawn) && ((endLocation.rank == Rank.EIGHT)  || (endLocation.rank == Rank.ONE)) )
    if (validPromotion) {
     return true
    } else { NonPromotablePieceException(toPromotePiece)}
    return false
  }

  private fun isCapture(movingPiece: Piece, board: Board): Boolean {
    return endLocation in movingPiece.allowedLocations(
      startLocation,
      board,
      true
    )
  }

  private fun getMovingPiece(location: Location, board: Board): Piece? {
    return board.getPiece(location)
  }
}

class IllegalMoveException(piece: String, endLoc: String) : Exception(
  "$piece can not be moved to $endLoc"
)

class SameColorCaptureException(endLoc: String, occupyingPiece: Piece) :
  Exception(
    "$endLoc is already occupied with ${occupyingPiece.description}"
  )

class WrongColorMovedException(movingPiece: Piece) : Exception(
  "You can not move a ${movingPiece.color} piece"
)

class NonExistentPieceException(startLoc: String) : Exception(
  "$startLoc does not contain a piece"
)

class NonPromotablePieceException(promoteTo: Piece) : Exception(
  "${promoteTo.description} is not a promotable piece."
)