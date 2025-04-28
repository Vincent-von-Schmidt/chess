package hwr.oop

import hwr.oop.board.Board

fun main(args: Array<String>) {

  """
    
    Possible commands: 
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to>
  
  """.trimIndent()

  if (args.isEmpty()) {
    println("No command provided. Try: chess new_game <id>");
    return
  }

  when (args.first()) {
    "new_game" -> {
      val id: Int

      if (args.size < 2) {
        println("Usage: chess new_game <id>");
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

      if (GameStorage.loadGame(id) == "game doesn't exist") {
        val newGame = Game(id)
      }

    }

    "game" -> {
      if (args.size < 3 || args[1] != "show") {
        println("Usage: chess game show <id>");
        return
      } else if (args.size > 3) {
        println("Error: game only takes 2 parameters!")
      }

      try {
        val id = args[2].toInt()
        // get game from id -> print board;
      } catch (e: NumberFormatException) {
        println("Error: game ID must be a valid integer!")
      }
    }

    "on" -> {
      if (args.size < 6 || args[2] != "move" || args[4] != "to") {
        println("Usage: chess on <id> move <from> to <to>");
        return
      }
      val id = args[1].toInt();
      val from = args[3].toInt();
      val to = args[5].toInt();
      // calculate moves

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