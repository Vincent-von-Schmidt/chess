package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class CheckTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `knight black throw from e4 to c5 on self check`() {
    // Given
    val fen = FEN("K7/4R3/8/8/4n3/8/8/4k3", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.E, Rank.FOUR)
    val endLocation = Location(File.C, Rank.FIVE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("Move would leave BLACK King in check")
  }

  @Test
  fun `knight black moves from d4 to c6 checks the opposite player`() {
    // Given
    val fen = FEN("3K4/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)

    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.C, Rank.SIX)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    val moveValidatedCheckStatus =
      board.movePiece(moveDesired, fen.activeColor).opponentInCheck

    // Then
    assertThat(moveValidatedCheckStatus).isTrue()
  }
}