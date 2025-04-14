package hwr.oop.fen

data class Fen (val notation: String) {
    val piecePlacement: List<String> = notation.split(" ").elementAt(0).split("/")
    val activeColor: Color = when (notation.split(" ").elementAt(1)) {
        "w" -> Color.WHITE
        "b" -> Color.BLACK
        else -> throw IllegalArgumentException("Not a valid color")
    }
    val castle: String = notation.split(" ").elementAt(2)
    val enpassant: String = notation.split(" ").elementAt(3)
    val halfmoves: Int = notation.split(" ").elementAt(4).toInt()
    val fullmoves: Int = notation.split(" ").elementAt(5).toInt()
}