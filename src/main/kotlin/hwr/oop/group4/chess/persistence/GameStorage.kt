package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE
import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import java.io.File

class GameStorage : SaveGamePort, LoadGamePort, DeleteGamePort {
    private val gamesFolder = File("games")
    private var filepath = GAMES_FILE

    override fun saveGame(id: Int, fen: String) {
        if (id >= TEST_NUMBER) filepath = GAMES_FILE_TEST
        if (loadGameFromFile(
                id,
                filepath
            ) != null
        ) throw GameAlreadyExistsException(id)
        saveGameToFile(id, fen, filepath)
    }

    override fun loadGame(id: Int): String {
        if (id >= TEST_NUMBER) filepath = GAMES_FILE_TEST
        val fen: String = loadGameFromFile(id, filepath)
            ?: throw GameDoesNotExistException(id)
        val fenProcessed = ReaderFEN(fen)
        var boardString = ""
        for (line in fenProcessed.piecePlacement) {
            var lineString = ""
            for (field in line) {
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

    override fun deleteGame(id: Int) {
        if (id >= TEST_NUMBER) filepath = GAMES_FILE_TEST
        val file = File(filepath)
        if (!file.exists()) return
        val lines = file.readLines().filter { !it.startsWith(id.toString()) }
        file.writeText(lines.joinToString("\n"))
    }

    private fun saveGameToFile(id: Int, fen: String, filepath: String) {
        val file = File(filepath)
        val needsNewline = file.length() > 0 && !file.readText().endsWith("\n")
        if (needsNewline) file.appendText("\n")
        file.appendText("$id;$fen\n")
    }

    private fun loadGameFromFile(id: Int, filepath: String): String? {
        gamesFolder.mkdirs()
        val file = File(filepath)
        if (!file.exists()) file.createNewFile()
        for (line in file.readLines()) {
            val parts = line.split(';')
            if (parts[0] == id.toString()) return parts[1]
        }
        return null
    }

    class GameAlreadyExistsException(id: Int) : Exception(
        "Game with ID $id already exists."
    )

    class GameDoesNotExistException(id: Int) : Exception(
        "Game with ID $id does not exist."
    )
}
