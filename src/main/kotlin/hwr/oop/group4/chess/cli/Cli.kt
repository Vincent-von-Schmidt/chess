package cli

import core.utils.Constants.STARTING_POSITION
import persistence.LoadGamePort
import persistence.SaveGamePort

class Cli(
  loadGamePort: LoadGamePort,
  saveGamePort: SaveGamePort,
  ){

  fun handle(args: List<String>, loadGamePort: LoadGamePort, saveGamePort: SaveGamePort) {
    if (args.isEmpty()) throw NoCommandException()

    when (args.first()) {
      "new_game" -> {
        val id: Int
        if (args.size < 2 || args.size > 2) throw NoCommandException()
        try {
          id = args[1].toInt()
        } catch (e: NumberFormatException) {
          throw WrongIdFormatException()
        }
        saveGamePort.saveGame(id, STARTING_POSITION)
        println("New game $id created.")

      }

      "game" -> {
        val id: Int
        if (args.size < 3 || args[1] != "show" || args.size > 3) throw NoCommandException()
        try {
          id = args[2].toInt()
        } catch (e: NumberFormatException) {
          throw WrongIdFormatException()
        }
        val gameFen: String = loadGamePort.loadGame(id)
        print(gameFen)
      }

      "on" -> {
        val id: Int
        if (args.size < 6 || args[2] != "move" || args[4] != "to" || args.size > 6) throw NoCommandException()
        try {
          id = args[1].toInt()
        } catch (e: NumberFormatException) {
          throw WrongIdFormatException()
        }
        val from = args[3]
        val to = args[5]
        TODO("Implement move logic")
      }

      else -> throw NoCommandException()
    }
  }
}

class WrongIdFormatException : Exception(
      """
        Error: <id> must be a valid integer!
        """.trimIndent()
)

class NoCommandException : Exception(
      """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to>
        """.trimIndent()
)
