package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GeneratorFENTest : AnnotationSpec() {

  @Test
  fun `generate fen from board`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(STARTING_POSITION)
    val expectedFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 0"

    // When
    val fen = GeneratorFEN.generateFen(board, "", "", 0, 0, Color.WHITE).toString()

    // Then
    assertThat(fen).isEqualTo(expectedFen)
  }

  @Test
  fun `generate fen from empty board`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(EMPTY_BOARD)
    val expectedFen = "8/8/8/8/8/8/8/8 w - - 0 0"

    // When
    val fen = GeneratorFEN.generateFen(board, "", "", 0, 0, Color.WHITE).toString()

    // Then
    assertThat(fen).isEqualTo(expectedFen)
  }
}
