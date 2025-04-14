package hwr.oop
import java.io.File

object GameStorage {
    private val saveDir = File("games")

    /*
    fun saveGame(game: Game, json: Json) {
        if (!saveDir.exists()) saveDir.mkdirs()
        val file = File(saveDir, "${game.id}.json")
        file.writeText(json.encodeToString(Game.serializer(), game))
    }

    fun loadGame(id: String, json: Json): Game {
        val file = File(saveDir, "$id.json")
        if (!file.exists()) throw IllegalArgumentException("Game with id '$id' not found.")
        return json.decodeFromString(Game.serializer(), file.readText())
    }
    */
}