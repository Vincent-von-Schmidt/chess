package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.pieces.Piece

class SameColorCaptureException(endLoc: String, occupyingPiece: Piece) :
  Exception(
    "$endLoc is already occupied with ${occupyingPiece.description}"
  )