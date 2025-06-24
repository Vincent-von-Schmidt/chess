package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.fen.FEN

object Constants {
  const val TEST_NUMBER = 1000000
  const val TEST_NUMBER_STRING = TEST_NUMBER.toString()

  val STARTING_POSITION = FEN(
    piecePlacement = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
    activeColor = Color.WHITE,
    castle = "-",
    enPassant = "-",
    halfMoves = 0,
    fullMoves = 0
  )

  val EMPTY_BOARD = FEN(
    piecePlacement = "8/8/8/8/8/8/8/8",
    activeColor = Color.WHITE,
    castle = "-",
    enPassant = "-",
    halfMoves = 0,
    fullMoves = 0
  )
}