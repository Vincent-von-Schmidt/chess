package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

class Field(val location: Location) {
  // TODO: private vars
  var top: Field? = null
  var bottom: Field? = null
  var left: Field? = null
  var right: Field? = null
  var piece: Piece? = null

  fun connectTop(field: Field) {
    this.top = field
  }

  fun connectBottom(field: Field) {
    this.bottom = field
  }

  fun connectLeft(field: Field) {
    this.left = field
  }

  fun connectRight(field: Field) {
    this.right = field
  }
}