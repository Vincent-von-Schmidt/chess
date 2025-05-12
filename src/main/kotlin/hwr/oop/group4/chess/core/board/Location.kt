package hwr.oop.group4.chess.core.board

data class Location(val x: Char, val y: Int) {

    init {
        require(x in 'a'..'h') { "x must be between 'a' and 'h'" }
        require(y in 1..8) { "y must be between 1 and 8" }
    }
    override fun toString(): String {
        return "$x$y"
    }
}

