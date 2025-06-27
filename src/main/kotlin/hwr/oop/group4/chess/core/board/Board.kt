package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.move.MoveDesiredValidator.validateMove
import hwr.oop.group4.chess.core.move.MoveResult
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.StringParser.parsePieceCharFromPiece
import hwr.oop.group4.chess.core.utils.opposite

class Board(private val piecePlacementMap: Map<Location, Piece>) : BoardView {

  private val fields: Map<Location, Field> = generateBoard()

  init {
    this.initializeWithPieces(piecePlacementMap)
  }

  private fun generateBoard(): Map<Location, Field> {
    val fieldMap = mutableMapOf<Location, Field>()

    // create fields with lazy providers
    Rank.entries.forEach { rank ->
      File.entries.forEach { file ->
        val loc = Location(file, rank)
        fieldMap[loc] = Field(loc) {
          mapOf(
            Direction.TOP to fieldMap[rank.next()?.let { Location(file, it) }],
            Direction.BOTTOM to fieldMap[rank.previous()
              ?.let { Location(file, it) }],
            Direction.LEFT to fieldMap[file.previous()
              ?.let { Location(it, rank) }],
            Direction.RIGHT to fieldMap[file.next()?.let { Location(it, rank) }]
          )
        }
      }
    }
    return fieldMap.toMap()
  }

  private fun initializeWithPieces(pieces: Map<Location, Piece>) {
    for ((location, piece) in pieces) {
      val field = getField(location)
      placePieceToField(field.location, piece)
    }
  }

  override fun getField(location: Location): Field {
    return fields[location]!!
  }

  override fun getPiece(location: Location): Piece? {
    return getField(location).getPiece()
  }

  fun boardToAscii(): String {
    val boardLines = mutableListOf<String>()
    for (rank in Rank.entries.reversed()) {
      val line = buildString {
        for (file in File.entries) {
          val location = Location(file, rank)
          val piece = getPiece(location)
          append((piece?.let { parsePieceCharFromPiece(it) } ?: '-') + " ")
        }
      }.trimEnd()
      boardLines.add(line)
    }
    return boardLines.joinToString("\n") + "\n"
  }

  private fun removePieceFromField(location: Location) {
    getField(location).placePiece(null)
  }

  private fun placePieceToField(location: Location, piece: Piece) {
    getField(location).placePiece(piece)
  }

  fun movePiece(
    moveDesired: MoveDesired,
    playerAtTurnColor: Color,
    promoteToPiece: Piece? = null,
  ): MoveResult {

    val validatedMove =
      validateMove(this, moveDesired, playerAtTurnColor, promoteToPiece)
    val boardState = BoardStateEvaluator(this)

    boardState.testSelfCheck(validatedMove, playerAtTurnColor)

    placePieceToField(validatedMove.endLocation, validatedMove.toPlacePiece)
    removePieceFromField(validatedMove.startLocation)

    val opponentInCheck = boardState.isCheck(playerAtTurnColor.opposite())

    val isCheckmate = boardState.isCheckmate(playerAtTurnColor.opposite())

    return MoveResult(
      move = validatedMove,
      opponentInCheck = opponentInCheck,
      isCheckmate = isCheckmate
    )
  }

  override fun simulateMoveAndCheck(
    from: Location,
    to: Location,
    piece: Piece,
    condition: () -> Boolean,
  ): Boolean {
    val fieldFrom = getField(from)
    val fieldTo = getField(to)

    val originalFrom = fieldFrom.getPiece()
    val originalTo = fieldTo.getPiece()

    // Simulate move
    removePieceFromField(fieldFrom.location)
    placePieceToField(fieldTo.location, piece)

    val result = condition()

    // Revert move
    removePieceFromField(to)
    if (originalFrom != null) {
      placePieceToField(from, originalFrom)
    }
    if (originalTo != null) {
      placePieceToField(to, originalTo)
    }

    return result
  }
}