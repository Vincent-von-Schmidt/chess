package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.fen.FEN

object Constants {
  const val TEST_NUMBER = 1000000
  const val GAMES_FILE_TEST = "games/gamesTest.csv"
  const val GAMES_FILE = "games/games.csv"

  val STARTING_POSITION = FEN(
    piecePlacement = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
    activeColor = Color.WHITE,
    castle = "KQkq",
    enPassant = "-",
    halfMoves = 0,
    fullMoves = 1
  )

  val EMPTY_BOARD = FEN(
    piecePlacement = "8/8/8/8/8/8/8/8",
    activeColor = Color.WHITE,
    castle = "KQkq",
    enPassant = "-",
    halfMoves = 0,
    fullMoves = 1
  )
}