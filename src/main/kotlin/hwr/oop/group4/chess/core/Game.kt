package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.player.Turn
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.StringParser

class Game(
  val id: Int,
  val fen: String = STARTING_POSITION,
) {
  val board: Board = Board(fen = fen)
  private val players: List<Player> = listOf(
    Player(1, Color.WHITE),
    Player(2, Color.BLACK)
  )
  val turn = Turn(players)

  fun move(from: String, to: String): Boolean {
    val startLocation = StringParser().parseLocation(from)
    val endLocation = StringParser().parseLocation(to)
    val move = Move(startLocation, endLocation)

    return movePiece(move)
  }

  fun movePiece(move: Move): Boolean {
    val playerAtTurn = turn.currentPlayer
    move.validateMove(board, playerAtTurn)
    board.movePiece(move)
    turn.switchTurn()
    return true
  }

  fun boardToString(): String {
    val piecePlacement = ReaderFEN(fen).piecePlacement
    var boardString = ""
    for (rank in piecePlacement) {
      var lineString = ""
      for (field in rank) {
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
}