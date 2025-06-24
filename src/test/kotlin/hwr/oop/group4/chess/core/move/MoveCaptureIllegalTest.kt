package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveCaptureIllegalTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `pawn black throw on wrong capture white queen`() {
    // Given
    val fen = FEN("8/8/8/3p4/3Q4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    val board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.FOUR)

    // When / Then
    val moveDesired = MoveDesired(startLocation, endLocation)
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Pawn can not be moved to D4")
  }

  @Test
  fun `knight white throw on capture black king`() {
    // Given
    val fen = FEN("8/8/8/8/5k2/3N4/8/8", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.F, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE Knight can not be moved to F4")
  }
}
