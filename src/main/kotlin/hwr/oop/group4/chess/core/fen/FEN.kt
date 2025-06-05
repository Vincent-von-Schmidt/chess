package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.utils.Color

data class FEN(
  val piecePlacement: String,
  val activeColor: Color,
  val castle: String,
  val enPassant: String,
  val halfMoves: Int,
  val fullMoves: Int
)
