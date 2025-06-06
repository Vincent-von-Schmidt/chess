package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GeneratorFENTest : AnnotationSpec() {

  @Test
  fun `generate fen from board`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(STARTING_POSITION)

    // When
    val fen = GeneratorFEN.generateFen(board, "", "", 0, 1, Color.WHITE)

    // Then
    assertThat(fen.asString()).isEqualTo("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1")
  }

  @Test
  fun `generate fen from empty board`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(EMPTY_BOARD)

    // When
    val fen = GeneratorFEN.generateFen(board, "", "", 0, 1, Color.WHITE)

    // Then
    assertThat(fen.asString()).isEqualTo("8/8/8/8/8/8/8/8 w - - 0 1")
  }
}
