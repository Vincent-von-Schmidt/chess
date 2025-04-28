package hwr.oop
import java.io.File

object GameStorage {
    private val saveDir = File("games")

    fun saveGame(game: Game) {
        saveDir.mkdirs()
    }

    fun loadGame(id: Int): MutableMap<String, String> {
        TODO("json data loading")
        // load the game from json format
    }
}