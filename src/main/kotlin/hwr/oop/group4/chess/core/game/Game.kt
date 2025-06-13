package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.GeneratorFEN.generateFen
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.move.MoveResult
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.player.PlayerToMove
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.persistence.GameStorage

class Game(
  val id: Int,
  var fen: FEN = STARTING_POSITION,
) {
  val board: Board = BoardFactory.generateBoardFromFen(fen)

  private val white = Player(1, Color.WHITE)
  private val black = Player(2, Color.BLACK)
  val current = if (fen.activeColor == Color.WHITE) white else black
  private val players = PlayerToMove(white, black, current)

  var recentFENs: MutableList<FEN> = mutableListOf()

  // TODO("update these properties after each move")

  private val castle = ""
  private val enPassant = ""
  private val halfMoveClock = 0
  private val fullMoveNumber = 1

  fun movePiece(moveDesired: MoveDesired, promoteTo: Piece? = null): Boolean {

    val moveResult = board.movePiece(moveDesired, players.getCurrentColor(), promoteTo)

    players.switchTurn()

    this.fen = updateFen()
    val updatedGame = GameStorage.saveGame(this, newGame = false)

    updateGameState(updatedGame.recentFENs, moveResult)

    return true
  }

  fun boardToAscii(): String {
    val piecePlacement = fen.piecePlacement.split("/")
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

  private fun isThreefoldRepetition(recentFENs: MutableList<FEN>) : Boolean {
    val result = recentFENs.groupingBy { it }
      .eachCount()
      .any { it.value >= 3 }
    return result
  }

  private fun isFiftyMoveRule() : Boolean {
    return false
  }

  private fun updateFen(): FEN {
    return generateFen(
      this.board,
      castle,
      enPassant,
      halfMoveClock,
      fullMoveNumber,
      players.getCurrentColor()
    )
  }

  private fun updateGameState(recentFENs: MutableList<FEN>, moveResult: MoveResult): GameState {
    return when {
      moveResult.isCheckmate -> {
        val state = GameState.CHECKMATE
        val winner = players.getCurrentColor()
        GameStorage.saveGame(this, false)
        throw GameOverException(null, state, winner) // Sofortiges Spielende
      }

      moveResult.opponentInCheck -> { GameState.CHECK }

      isThreefoldRepetition(recentFENs) -> {
        val state = GameState.DRAW
        GameStorage.saveGame(this, false)
        throw GameOverException(DrawReason.THREEFOLD_REPETITION, state, null)
      }

      isFiftyMoveRule() -> {
        val state = GameState.DRAW
        GameStorage.saveGame(this, false)
        throw GameOverException(DrawReason.FIFTY_MOVE_RULE, state, null)
      }

      else -> GameState.NORMAL
    }
  }
}
