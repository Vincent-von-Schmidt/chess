package hwr.oop.fen

class Position (notation: String) {
    val col: Int = notation.elementAt(0).lowercaseChar() - 'a' + 1
    val row: Int = notation.elementAt(1).digitToInt()
}