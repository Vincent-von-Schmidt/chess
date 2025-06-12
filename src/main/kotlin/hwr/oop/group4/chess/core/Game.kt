package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.GeneratorFEN.generateFen
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Turn
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.persistence.GameStorage

class Game(
  val id: Int,
  var fen: FEN = STARTING_POSITION,
) {
  var board: Board = BoardFactory.generateBoardFromFen(fen)

  // private val players: List<Player> = listOf(
  //   Player(1, Color.WHITE),
  //   Player(2, Color.BLACK)
  // )
  val turn = Turn(fen)
  var recentFENs: MutableList<FEN> = mutableListOf()

  // TODO("update these properties after each move")

  private val castle = ""
  private val enPassant = ""
  private val halfMoveClock = 0
  private val fullMoveNumber = 1

  fun movePiece(move: Move, promoteTo: Piece? = null): Boolean {

    board.movePiece(move, turn.colorToMove, promoteTo)

    turn.switchTurn()

    this.fen = generateFen(
      this.board,
      castle,
      enPassant,
      halfMoveClock,
      fullMoveNumber,
      turn.colorToMove
    )
    val updatedGame = GameStorage.saveGame(this, newGame = false)

    if (checkForDraw(updatedGame.recentFENs)) throw DrawException()

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

  private fun checkForDraw(recentFENs: MutableList<FEN>): Boolean {
    // Threefold Repetition Rule
    return recentFENs.groupingBy { it }
      .eachCount()
      .any { it.value >= 3 }

    // Stalemate Rule

    // 50-Move Rule

    // Insufficient Material Rule
  }
}
