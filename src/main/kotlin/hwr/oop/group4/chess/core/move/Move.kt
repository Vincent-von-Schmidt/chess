package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

class Move(
  val startLocation: Location,
  val endLocation: Location,
)

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
  "You can not promote a ${promoteTo.description}."
)

class NoPromoteChoiceException(movingPiece: Piece) : Exception(
  "${movingPiece.description} has no piece to promote to"
)