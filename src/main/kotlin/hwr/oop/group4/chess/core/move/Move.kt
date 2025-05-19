package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Player

class Move(
  val startLocation: Location,
  val endLocation: Location,
  val movingPiece: Piece, // muss ausm board selbst bestimmt werden
  val capture: Boolean = false, // muss ausm board selbst bestimmt werden
) {
  private val piece = movingPiece.description
  private val startLoc = startLocation.description
  private val endLoc = endLocation.description

  fun validateMove(board: Board, playerAtTurn: Player) {
    if (board.getField(startLocation).piece != movingPiece) {
      throw NonExistentPieceException(startLoc, piece)
    }

    if (movingPiece.color != playerAtTurn.color) {
      throw WrongColorMovedException(movingPiece)
    }

    val occupyingPiece = board.getField(endLocation).piece

    if (occupyingPiece != null) {
      if (occupyingPiece.color == playerAtTurn.color) {
        throw SameColorCaptureException(endLoc, occupyingPiece)
      } else if (occupyingPiece.color != playerAtTurn.color && !capture) {
        throw CaptureException(endLoc, occupyingPiece)
        // This doesn't make sense, bc the user only runs the program with each command.
        // He can't respond to a question. So the capture needs to be automatic.
        // This code has to be rewritten...
      }
    }


    if (endLocation !in movingPiece.allowedLocations(
        startLocation,
        board,
        capture
      )
    ) {
      throw IllegalMoveException(piece, endLoc)
    }
  }
}

class IllegalMoveException(piece: String, endLoc: String) : Exception(
  "$piece can not be moved to $endLoc"
)

class CaptureException(endLoc: String, occupyingPiece: Piece) : Exception(
  "$endLoc is already occupied with ${occupyingPiece.description}, do you want to capture?"
)

class SameColorCaptureException(endLoc: String, occupyingPiece: Piece) :
  Exception(
    "$endLoc is already occupied with ${occupyingPiece.description}"
  )

class WrongColorMovedException(movingPiece: Piece) : Exception(
  "You can not move a ${movingPiece.color} piece"
)

class NonExistentPieceException(startLoc: String, piece: String) : Exception(
  "$startLoc does not contain a $piece"
)
