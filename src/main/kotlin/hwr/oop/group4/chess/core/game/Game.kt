package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.GeneratorFEN.generateFen
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.move.MoveResult
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.persistence.GameStorage.saveGame

class Game(
  val id: Int,
  var fen: FEN = STARTING_POSITION,
) {
  val board: Board = BoardFactory.generateBoardFromFen(fen)

  private val whitePlayer = Player(1, Color.WHITE)
  private val blackPlayer = Player(2, Color.BLACK)
  private var currentPlayer = if (fen.activeColor == Color.WHITE) whitePlayer else blackPlayer
  private val playerScores = mutableMapOf(
    whitePlayer to 0,
    blackPlayer to 0
  )

  // TODO("update these properties after each move")

  private var castle = fen.castle
  private var enPassant = fen.enPassant
  private var halfMoveClock = fen.halfMoves
  private var fullMoveNumber = fen.fullMoves

  var recentFENs: MutableList<FEN> = mutableListOf() // TODO make secure
  // public var recent fens is cheatable loadGame should pass the list on load of a
  // game, then inside the actual game there will be updates to set list
  // which then should be able to bo saved...
  // similar with playerScores etc.

  fun movePiece(moveDesired: MoveDesired, promoteTo: Piece? = null): Boolean {

    val moveResult =
      board.movePiece(moveDesired, currentPlayer.getColor(), promoteTo)
    updatePlayersAfterMove(moveResult.move.pieceCaptured)
    this.fen = updateFen()
    val saveGame = saveGame(this, newGame = false) // TODO save GameState (down at gameEnd) and PlayerScore
    updateGameState(saveGame.recentFENs, moveResult)
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

  private fun isThreefoldRepetition(recentFENs: MutableList<FEN>): Boolean {
    val result = recentFENs.groupingBy { it }
      .eachCount()
      .any { it.value >= 3 }
    return result
  }

  private fun isFiftyMoveRule(): Boolean {
    return halfMoveClock >= 50
  }

  private fun updateFen(): FEN {
    return generateFen(
      this.board,
      castle,
      enPassant,
      halfMoveClock,
      fullMoveNumber,
      currentPlayer.getColor(),
    )
  }

  private fun updatePlayersAfterMove(pieceCaptured: Piece?) {
    if (pieceCaptured != null) {
      val currentScore = playerScores[currentPlayer] ?: 0
      playerScores[currentPlayer] = currentScore + pieceCaptured.getValue()
    }

    if (currentPlayer == blackPlayer) {
      fullMoveNumber++
    }

    switchTurn()
  }

  private fun switchTurn() {
    currentPlayer = if (currentPlayer.getColor() == Color.WHITE) blackPlayer else whitePlayer
  }

  fun getCurrentPlayerColor(): Color {
    return currentPlayer.getColor()
  }

  private fun updateGameState(
    recentFENs: MutableList<FEN>,
    moveResult: MoveResult,
  ): GameState {
    return when {
      moveResult.isCheckmate -> {
        val state = GameState.CHECKMATE
        val winnerColor = currentPlayer.getColor()
        saveGame(this, false)
        throw GameOverException(null, state, winnerColor)
      }

      moveResult.opponentInCheck -> {
        GameState.CHECK
      }

      isThreefoldRepetition(recentFENs) -> {
        val state = GameState.DRAW
        saveGame(this, false)
        // TODO safe the game state as well to determine either the game can be loaded back as playable
        // saveGame(this, false, state)?
        throw GameOverException(DrawReason.THREEFOLD_REPETITION, state, null)
      }

      isFiftyMoveRule() -> {
        val state = GameState.DRAW
        saveGame(this, false)
        throw GameOverException(DrawReason.FIFTY_MOVE_RULE, state, null)
      }

      else -> GameState.NORMAL
    }
  }
}
