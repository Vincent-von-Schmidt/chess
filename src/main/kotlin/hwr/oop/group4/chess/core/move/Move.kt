package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Piece

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

  fun isPromotion(): Boolean {
    return (this.startLocation.rank == Rank.SEVEN && this.endLocation.rank == Rank.EIGHT) || (this.startLocation.rank == Rank.TWO && this.endLocation.rank == Rank.ONE)
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
