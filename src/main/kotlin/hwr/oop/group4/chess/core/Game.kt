package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.fen.GeneratorFEN
import hwr.oop.group4.chess.core.fen.ReaderFEN
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.player.Turn
import hwr.oop.group4.chess.core.utils.AsciiArtFEN
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.StringParser

class Game(
  val id: Int,
  val fen: String = STARTING_POSITION,
) {
  val board: Board = Board(fen)
  private val players: List<Player> = listOf(
    Player(1, Color.WHITE),
    Player(2, Color.BLACK)
  )
  val turn = Turn(players)

  fun movePiece(move: Move): Boolean {
    val playerAtTurn = turn.currentPlayer
    move.validateMove(board, playerAtTurn)
    board.movePiece(move)
    turn.switchTurn()
    return true
  }

  private fun getBoardFEN(): String {
    return GeneratorFEN().generateFEN(board)
  }

  fun asciiArtFEN(): String {
    val piecePlacement = ReaderFEN(getBoardFEN()).piecePlacement
    return AsciiArtFEN().boardToString(piecePlacement)
  }
}