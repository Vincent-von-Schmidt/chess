package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.game.Game
import hwr.oop.group4.chess.core.game.GameOverException
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.StringParser
import hwr.oop.group4.chess.persistence.GamePersistencePort

class Cli(
  private val gameStorage: GamePersistencePort,
) {

  fun handle(args: List<String>) {
    if (args.isEmpty()) throw InvalidCommandException()

    when (args.first()) {
      "new_game" -> {
        val id: Int
        if (args.size < 2 || args.size > 2) throw InvalidCommandException()
        try {
          id = args[1].toInt()
        } catch (e: NumberFormatException) {
          throw WrongIdFormatException()
        }
        val game = Game(id)
        gameStorage.saveGame(game, newGame = true)
        println("New game $id created.")
      }

      "game" -> {
        val id: Int
        if (args.size < 3 || args[1] != "show" || args.size > 3) throw InvalidCommandException()
        try {
          id = args[2].toInt()
        } catch (e: NumberFormatException) {
          throw WrongIdFormatException()
        }
        val game = gameStorage.loadGame(id)
        val gameString = game.boardToAscii()
        print(gameString)
        println("${game.current.color} to move.")
      }

      "on" -> {
        val id: Int
        if (args.size < 6 || args[2] != "move" || args[4] != "to" || args.size > 7) throw InvalidCommandException()
        try {
          id = args[1].toInt()
        } catch (e: NumberFormatException) {
          throw WrongIdFormatException()
        }
        val from = StringParser.parseLocationFromString(args[3])
        val to = StringParser.parseLocationFromString(args[5])
        val moveDesired = MoveDesired(from, to)
        val game = gameStorage.loadGame(id)
        val playerToMove = game.current.color

        val promoteTo: Piece? =
          if (args.size == 7) StringParser.parsePromotionPieceFromString(args[6], color = playerToMove) else null
        try {
          game.movePiece(moveDesired, promoteTo)
        } catch (e: GameOverException) {
          println(e.message)
          gameStorage.deleteGame(game)
          return
        } catch (e: Exception) {
          println("Invalid move from ${from.description} to ${to.description}.")
          return
        }
        println("Move from ${from.description} to ${to.description} executed.")
      }

      else -> throw InvalidCommandException()
    }
  }
}