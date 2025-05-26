package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GeneratorFENTest : AnnotationSpec() {

  @Test
  fun `pieces from fen placed correctly`() {
    val board = Board()
    val fenWrite = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
    val fenRead = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"

    val piecePlacement = ReaderFEN(fenWrite).piecePlacement

    LoaderFEN.placePieces(piecePlacement, board)

    val generator = GeneratorFEN()
    val fen = generator.generateFEN(board)

    assertThat(fen).isEqualTo(fenRead)
  }
}
