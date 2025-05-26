package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.fen.GeneratorFEN
import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.player.Turn
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION

class Game(
  val id: Int,
  var fen: String = STARTING_POSITION,
) {
  var board: Board = Board(fen)
  private val players: List<Player> = listOf(
    Player(1, Color.WHITE),
    Player(2, Color.BLACK)
  )
  val turn = Turn(players)
  // TODO("get turn from fen")

  // TODO("update these properties after each move")
  private val castle = ""
  private val enPassant = ""
  private val halfMoveClock = 0
  private val fullMoveNumber = 1

  fun movePiece(move: Move): Boolean {
    val playerAtTurn = turn.currentPlayer
    move.validateMove(board, playerAtTurn)
    board.movePiece(move)
    turn.switchTurn()
    this.fen = GeneratorFEN.generateFen(
      this.board,
      castle,
      enPassant,
      halfMoveClock,
      fullMoveNumber,
      turn.currentPlayer.color
    )
    this.board = Board(fen = this.fen)
    return true
  }

  fun boardToString(): String {
    val piecePlacement = ReaderFEN(fen).piecePlacement
    var boardString = ""
    for (rank in piecePlacement) {
      var lineString = ""
      for (field in rank) {
        if (field in '1'..'8') {
          repeat(field.digitToInt()) { lineString += "- " }
        } else {
          lineString += "$field "
        }
      }
      boardString += "$lineString\n"
    }
    return boardString
  }
}