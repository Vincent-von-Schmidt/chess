package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.toFenChar

data class FEN(
  val piecePlacement: String,
  val activeColor: Color,
  val castle: String,
  val enPassant: String,
  val halfMoves: Int,
  val fullMoves: Int
)

fun FEN.asString(): String =
  "$piecePlacement ${activeColor.toFenChar()} ${castle.ifEmpty { "-" }} ${enPassant.ifEmpty { "-" }} $halfMoves $fullMoves"