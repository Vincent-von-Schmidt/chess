package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GeneratorFENTest : AnnotationSpec() {

  @Test
  fun `pieces from fen placed correctly`() {
    // Given
    val board = Board()
    val generator = GeneratorFEN()
    val fenWrite = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
    val expectedPiecePlacement = listOf("rnbqkbnr", "pppppppp", "8", "8", "8", "8", "PPPPPPPP", "RNBQKBNR")

    // When
    val piecePlacementWrite = ReaderFEN(fenWrite).piecePlacement
    LoaderFEN.placePieces(piecePlacementWrite, board)
    val generatedFen = generator.generateFEN(board)
    val generatedFenPiecePlacement = ReaderFEN(generatedFen).piecePlacement

    // Then
    assertThat(generatedFenPiecePlacement).isEqualTo(expectedPiecePlacement)
  }
}
