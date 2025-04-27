package hwr.oop.fen

class Fen (notation: String) {
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

    fun get_piece_on_field(position: Position): String {
        val row: String = this.piecePlacement.elementAt(position.row)

        var row_full: String = ""
        for (field in row) {
            if (field in '0'..'9') {
                row_full += "".padStart(field.digitToInt())
            } else {
                row_full += "$field"
            }
        }

        val piece: String =  when (row_full.elementAt(position.col).toString()) {
            "" -> "_"
            else -> row_full.elementAt(position.col).toString()
        }

        println(piece)

        return ""
    }
}