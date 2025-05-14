package core.board

import core.location.*
import core.pieces.Piece

class Field(val location: Location) {
    var top: Field? = null
    var bottom: Field? = null
    var left: Field? = null
    var right: Field? = null
    var piece: Piece? = null
}
