package group4.chess.board

data class Location(val x: Char, val y: Int) {
    init {
        require(x in 'a'..'h') { "x must be between 'a' and 'h'" }
        require(y in 1..8) { "y must be between 1 and 8" }
    }
}