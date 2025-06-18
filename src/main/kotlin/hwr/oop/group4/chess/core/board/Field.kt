package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

class Field(val location: Location) {
  var top: Field? = null // TODO public var directions as well as placePiece.. what should i do bout it?
  var bottom: Field? = null
  var left: Field? = null
  var right: Field? = null
  private var piece: Piece? = null

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

  fun getPiece(): Piece? {
    return this.piece
  }

  fun placePiece(piece: Piece?) {
    this.piece = piece
  }
}