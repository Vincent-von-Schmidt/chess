package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.fen.ReaderFEN

class AsciiArtFEN {

    fun boardToString(piecePlacement: List<String>): String {
        var boardString = ""
        for (rank in piecePlacement) {
            var lineString = ""
            for (field in rank) {
                if (field in '1'..'8') {
                    repeat(field.digitToInt()) { lineString += "  " }
                } else {
                    lineString += "$field "
                }
            }
            boardString += "$lineString\n"
        }
        return boardString
    }
}