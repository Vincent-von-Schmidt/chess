package cli

import core.utils.Constants.STARTING_POSITION
import persistence.LoadGamePort
import persistence.SaveGamePort

class Cli(
  loadGamePort: LoadGamePort,
  saveGamePort: SaveGamePort,
  ){

  fun handle(args: List<String>, loadGamePort: LoadGamePort, saveGamePort: SaveGamePort) {
    if (args.isEmpty()) {
      println("No command provided. Try: chess new_game <id>")
      return
    }

    when (args.first()) {
      "new_game" -> {
        val id: Int

        if (args.size < 2) {
          println("Usage: chess new_game <id>")
          return
        } else if (args.size > 2) {
          println("Error: new_game only takes 1 parameter!")
          return
        }

        try {
          id = args[1].toInt()
        } catch (e: NumberFormatException) {
          println("Error: new_game ID must be a valid integer!")
          return
        }

        try {
          saveGamePort.saveGame(id, STARTING_POSITION)
        } catch (e: IllegalArgumentException) {
          println("Error: game ID is already in use")
          return
        }

        println("New game $id created.")

      }

      "game" -> {
        val id: Int
        val output: String
        if (args.size < 3 || args[1] != "show") {
          println("Usage: chess game show <id>")
          return
        } else if (args.size > 3) {
          println("Error: game show only takes 1 parameter!")
          return
        }

        try {
          id = args[2].toInt()
        } catch (e: NumberFormatException) {
          println("Error: game ID must be a valid integer!")
          return
        }

        try {
          output = loadGamePort.loadGame(id).toString()
        } catch (e: IllegalArgumentException) {
          println("Error: game ID is not in use!")
          return
        }

        print(output)

      }

      "on" -> {
        if (args.size < 6 || args[2] != "move" || args[4] != "to") {
          println("Usage: chess on <id> move <from> to <to>");
          return
        }
        val id = args[1].toInt()
        val from = args[3].toInt()
        val to = args[5].toInt()

        // if (move = successful) {
        //   Game.saveGame();
        //   println("Move $from -> $to successful.");
        // } else {
        //   println("Move failed");
        // }
      }

      else -> {
        println("Error: unknown command \"${args.first()}\"");
      }
    }
  }
}