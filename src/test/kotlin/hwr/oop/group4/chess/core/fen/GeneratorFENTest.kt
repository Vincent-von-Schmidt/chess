package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.pieces.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GeneratorFENTest : AnnotationSpec() {

  @Test
  fun `generate fen from board`() {
    // Given
    val board = Board()

    // When
    val fen = GeneratorFEN.generateFen(board, "", "", 0, 1, Color.WHITE)

    // Then
    assertThat(fen).isEqualTo("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1")
  }
}
