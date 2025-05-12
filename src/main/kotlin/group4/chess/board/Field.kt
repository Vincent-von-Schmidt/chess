package group4.chess.board

import group4.chess.location.*
import group4.chess.pieces.Piece

class Field(val location: Location) {
    var top: Field? = null
    var bottom: Field? = null
    var left: Field? = null
    var right: Field? = null
    var piece: Piece? = null
}