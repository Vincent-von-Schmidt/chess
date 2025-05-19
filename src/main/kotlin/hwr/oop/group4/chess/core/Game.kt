package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.player.Turn
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION

class Game(
  val id: Int,
  val board: Board = Board(),
  players: List<Player> = listOf(
    Player(1, Color.WHITE),
    Player(2, Color.BLACK)
  ),
  val fen: String = STARTING_POSITION,
) {
  val turn = Turn(players)

  fun move(from: String, to: String): Boolean {
    // - returns true if move was successful, throws otherwise
    // - utilizes movePiece(move: Move) but has to get Move beforehand
    TODO(
      "" +
          "implement move()" +
          "make movePiece(move: Move) private" +
          "change tests to use move(from: String, to: String) instead of movePiece(move: Move)"
    )
  }

  fun movePiece(move: Move) {
    val playerAtTurn = turn.currentPlayer
    move.validateMove(board, playerAtTurn)
    board.movePiece(move)
    turn.switchTurn()
  }

  fun boardToString(): String {
    val fenProcessed = ReaderFEN(fen)
    var boardString = ""
    for (line in fenProcessed.piecePlacement) {
      var lineString = ""
      for (field in line) {
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