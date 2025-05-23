package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

class Field(val location: Location) {
  var top: Field? = null
  var bottom: Field? = null
  var left: Field? = null
  var right: Field? = null
  var piece: Piece? = null
}