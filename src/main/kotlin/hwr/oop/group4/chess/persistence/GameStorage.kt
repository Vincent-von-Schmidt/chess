package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.utils.Constants.TESTNUMBERS
import hwr.oop.group4.chess.core.utils.Constants.GAMESTESTFILE
import hwr.oop.group4.chess.core.utils.Constants.GAMESFILE
import hwr.oop.group4.chess.core.fen.ReaderFEN

class GameStorage : SaveGamePort, LoadGamePort, DeleteGamePort {
    private var filepath = GAMESFILE

    override fun saveGame(id: Int, fen: String) {
        if (id >= TESTNUMBERS) {
            filepath = GAMESTESTFILE
        }
        if (loadGameFromFile(id, filepath) != null) {
            throw IllegalArgumentException("Game already exists.")
        } else {
            saveGameToFile(id, fen, filepath)
        }
    }

    override fun loadGame(id: Int): String {
        if (id >= TESTNUMBERS) {
            filepath = GAMESTESTFILE
        }

        val fen: String? = loadGameFromFile(id, filepath)

        if (fen == null) {
            throw IllegalArgumentException("Game not found.")
        } else {
            val fenProcessed = ReaderFEN(fen.toString())
            val sb = StringBuilder()
            for (line in fenProcessed.piecePlacement) {
                val lineFields = mutableListOf<String>()
                for (field in line) {
                    if (field in '0'..'9') {
                        repeat(field.digitToInt()) { lineFields.add("  ") }
                    } else {
                        lineFields.add("$field ")
                    }
                }
                val lineText = lineFields.joinToString("").removeSuffix(" ")
                sb.appendLine(lineText)
            }
            return(sb.toString().trimEnd())
        }
    }

    override fun deleteGame(id: Int) {
        if (id >= TESTNUMBERS) {
            filepath = GAMESTESTFILE
        }

        val file = java.io.File(filepath)
        if (!file.exists()) return

        val lines = file.readLines().filter { !it.startsWith(id.toString()) }
        file.writeText(lines.joinToString("\n"))
    }

    private fun saveGameToFile(id: Int, fen: String, filepath: String) {
        val file = java.io.File(filepath)
        file.appendText("\n$id;$fen\n")
    }

    private fun loadGameFromFile(id: Int, filepath: String): String? {
        val file = java.io.File(filepath)
        if (!file.exists()) return null

        for (line in file.readLines()) {
            val parts = line.split(';')
            if (parts[0] == id.toString()) {
                return parts[1]
            }
        }
        return null
    }
}