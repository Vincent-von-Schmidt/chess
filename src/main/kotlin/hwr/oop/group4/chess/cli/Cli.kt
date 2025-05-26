package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.utils.StringParser
import hwr.oop.group4.chess.persistence.LoadGamePort
import hwr.oop.group4.chess.persistence.SaveGamePort

class Cli(
  loadGamePort: LoadGamePort,
  saveGamePort: SaveGamePort,
) {

  fun handle(
    args: List<String>,
    loadGamePort: LoadGamePort,
    saveGamePort: SaveGamePort,
  ) {
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
        val game = Game(id)
        saveGamePort.saveGame(game, newGame = true)
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
        val game = loadGamePort.loadGame(id)
        val gameString = game.boardToString()
        print(gameString)
        println("${game.turn.colorToMove} to move.")
      }

      "on" -> {
        val id: Int
        if (args.size < 6 || args[2] != "move" || args[4] != "to" || args.size > 6) throw NoCommandException()
        try {
          id = args[1].toInt()
        } catch (e: NumberFormatException) {
          throw WrongIdFormatException()
        }
        val from = StringParser.parseLocation(args[3])
        val to = StringParser.parseLocation(args[5])
        val game = loadGamePort.loadGame(id)
        try {
          game.movePiece(Move(from, to))
        } catch (e: Exception) {
          println("Invalid move from ${from.description} to ${to.description}.")
          return
        }
        println("Move from ${from.description} to ${to.description} executed.")
        saveGamePort.saveGame(game, newGame = false)
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
