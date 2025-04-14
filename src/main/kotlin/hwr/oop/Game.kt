package hwr.oop

import hwr.oop.board.Board
import hwr.oop.player.Player
import java.io.File

data class Game (
    val id: Int
) {

    /*
    init {
        fun saveGame(game: Game, json: Json) {
            if (!saveDir.exists()) saveDir.mkdirs()
            val file = File(saveDir, "${game.id}.json")
            file.writeText(json.encodeToString(Game.serializer(), game))
        }
    }

     */
    val board = Board()
    val players = listOf(
        Player(1),
        Player(2)
    )
    var lastTurn = Move()
}