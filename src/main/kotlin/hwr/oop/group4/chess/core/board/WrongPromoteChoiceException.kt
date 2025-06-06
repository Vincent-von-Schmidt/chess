package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.pieces.Piece

class WrongPromoteChoiceException(movingPiece: Piece, promoteToPiece: Piece? = null) : Exception(
  if (promoteToPiece == null){
    "${movingPiece.description} has no Piece to promote to"} else {
    "${movingPiece.description} can not promote to ${promoteToPiece.description}"
  }

)