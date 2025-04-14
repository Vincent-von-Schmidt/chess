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
      if (args.size < 2) {
        println("Usage: chess new_game <id>");
        return
      }
      val id = args[1].toInt();
      Game(id);
      println("New game $id created.");
    }

    /*
    "game" -> {
      if (args.size < 3 || args[1] != "show") {
        println("Usage: chess game show <id>");
        return
      }
      val id = args[2].toInt();
      //val board = Board(id);
      //println(board);
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
      println("Unknown command: ${args.first()}");
    }
    */
  }
}