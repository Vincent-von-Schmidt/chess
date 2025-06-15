package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

class MoveValidated(
  val startLocation: Location,
  val endLocation: Location,
  val toPlacePiece: Piece,
  val pieceCaptured: Piece?,
)