package hwr.oop.group4.chess.core

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.GeneratorFEN.generateFen
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.player.Turn
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION

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
  var recentFens: MutableList<FEN> = mutableListOf()

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
}
