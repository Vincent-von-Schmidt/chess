package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.toChar

class FEN(
  val piecePlacement: String,
  val activeColor: Color,
  val castle: String,
  val enPassant: String,
  val halfMoves: Int,
  val fullMoves: Int,
) {

  override fun toString(): String {
    return "$piecePlacement ${activeColor.toChar()} ${castle.ifEmpty { "-" }} ${enPassant.ifEmpty { "-" }} $halfMoves $fullMoves"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is FEN) return false
    return piecePlacement == other.piecePlacement
  }

  override fun hashCode(): Int {
    return piecePlacement.hashCode()
  }
}