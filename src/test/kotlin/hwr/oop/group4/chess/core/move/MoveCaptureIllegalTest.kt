package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.pieces.Queen
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveCaptureIllegalTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `pawn black throw on wrong capture white queen`() {
    // Given
    val fen = FEN(
      piecePlacement = "8/8/8/3p4/3Q4/8/8/8",
      activeColor = Color.BLACK,
      castle = "-",
      enPassant = "-",
      halfMoves = 0,
      fullMoves = 1
    )
    val board = BoardFactory.generateBoardFromFen(fen)
    val pawn = Pawn(Color.BLACK)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.FOUR)

    // When / Then
    val moveDesired = MoveDesired(startLocation, endLocation)
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Pawn can not be moved to D4")
  }
}
