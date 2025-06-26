package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.GeneratorFEN.generateFen
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.move.MoveResult
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Player
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.persistence.GameStorage.saveGame
import hwr.oop.group4.chess.persistence.SaveEntry

class Game(
  val id: Int,
  gameSave : List<SaveEntry>,
) {
  private var fen: FEN = gameSave.lastOrNull()?.getFen() ?: STARTING_POSITION
  val board: Board = BoardFactory.generateBoardFromFen(fen)
  private val players = mapOf(
    Color.WHITE to Player(1, Color.WHITE),
    Color.BLACK to Player(2, Color.BLACK)
  )
  private var currentPlayer = players[fen.activeColor]!!
  private var lastPlayer = currentPlayer
  private val playerScores = players.values.associateWith { 0 }.toMutableMap()
  private var castle = fen.castle
  private var enPassant = fen.enPassant
  private var halfMoves = fen.halfMoves
  private var fullMoves = fen.fullMoves
  private var state: GameState = gameSave.lastOrNull()?.getGameState() ?: GameState.NORMAL
  private var saveEntries: MutableList<SaveEntry> = if (gameSave.isNotEmpty()) {
    gameSave.toMutableList()
  } else {
    mutableListOf(SaveEntry(fen, 0, 0, GameState.NORMAL))
  }
  private var recentFENs: MutableList<FEN> = saveEntries.map { it.getFen() }.toMutableList()

  fun getFen(): FEN{
    updateFen()
    return fen
  }

  fun movePiece(moveDesired: MoveDesired, promoteTo: Piece? = null): Boolean { // TODO looks crowded
    val moveResult = board.movePiece(moveDesired, currentPlayer.getColor(), promoteTo)
    updateHalfMoves(moveResult.move.pieceCaptured, moveResult.move.toPlacePiece)
    updateFullMoves()
    updatePlayers(moveResult.move.pieceCaptured)
    updateFen()
    updateSaveEntries(state)
    val drawReason = updateGameState(moveResult).second
    saveGame(this, false)
    updateGameEnd(state, drawReason)
    return true
  }

  fun getCurrentPlayerColor(): Color {
    return currentPlayer.getColor()
  }

  fun getPlayerScore(color: Color): Int {
    val player = players[color]!!
    return playerScores[player]!!
  }

  private fun isThreefoldRepetition(): Boolean {
    return recentFENs.groupingBy { it }
      .eachCount()
      .any { it.value >= 3 }
  }

  private fun isFiftyMoveRule(): Boolean {
    return halfMoves >= 50
  }

  private fun updateFullMoves() {
    if (currentPlayer.getColor() == Color.BLACK) {
      fullMoves++
    }
  }

  private fun updateHalfMoves(pieceCaptured: Piece?, movedPiece: Piece?) {
    halfMoves = if (pieceCaptured != null || movedPiece is Pawn) {
      0
    } else {
      halfMoves + 1
    }
  }

  private fun updateFen(): FEN {
    this.fen = generateFen(
      this.board,
      castle,
      enPassant,
      halfMoves,
      fullMoves,
      currentPlayer.getColor(),
    )
    return fen
  }

  private fun updateSaveEntries(state: GameState) {
    if (saveEntries.lastOrNull()?.getFen() != fen) {
      saveEntries.add(
        SaveEntry(
          fen,
          getPlayerScore(Color.WHITE),
          getPlayerScore(Color.BLACK),
          state
        )
      )
    }
    recentFENs = saveEntries.map { it.getFen() }.toMutableList()
  }

  fun getSaveEntries(): List<SaveEntry> = saveEntries

  private fun updatePlayers(pieceCaptured: Piece?) {
    lastPlayer = currentPlayer
    if (pieceCaptured != null) {
      val currentScore = playerScores[currentPlayer] ?: 0
      playerScores[currentPlayer] = currentScore + pieceCaptured.getValue()
    }
    switchTurn()
  }

  private fun switchTurn() {
    currentPlayer =
      if (currentPlayer.getColor() == Color.WHITE) players[Color.BLACK]!! else players[Color.WHITE]!!
  }

  private fun updateGameState(moveResult: MoveResult): Pair<GameState, DrawReason?> {
    val (newState, drawReason) = when {
      moveResult.isCheckmate -> GameState.CHECKMATE to null
      moveResult.opponentInCheck -> GameState.CHECK to null
      isThreefoldRepetition() -> GameState.DRAW to DrawReason.THREEFOLD_REPETITION
      isFiftyMoveRule() -> GameState.DRAW to DrawReason.FIFTY_MOVE_RULE
      else -> GameState.NORMAL to null
    }
    state = newState
    return newState to drawReason
  }

  private fun updateGameEnd(state: GameState, reason: DrawReason? = null) {
    when (state) {
      GameState.CHECKMATE -> {
        val winnerColor = lastPlayer.getColor()
        throw CheckMateException(state, winnerColor)
      }
      GameState.DRAW -> {
        throw DrawException(state, reason)
      }
      else -> {  // Do nothing for NORMAL or CHECK
      }
    }
  }
}