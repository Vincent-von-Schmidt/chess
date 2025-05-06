package group4.chess.board

import group4.chess.location.*

class Field(val location: Location) {
    var top: Field? = null
    var bottom: Field? = null
    var left: Field? = null
    var right: Field? = null
    var topLeft: Field? = null
    var topRight: Field? = null
    var bottomLeft: Field? = null
    var bottomRight: Field? = null
}