package hwr.oop.group4.chess.core.move

class MoveResult (
  val move : MoveValidated,
  val opponentInCheck: Boolean,
  val isCheckmate: Boolean
)