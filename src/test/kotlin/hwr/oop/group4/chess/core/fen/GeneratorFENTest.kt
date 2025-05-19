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

    val loaderFEN = LoaderFEN()
    val readerFEN = ReaderFEN(fenWrite)

    loaderFEN.placePieces(readerFEN, board)

    val generator = GeneratorFEN()
    val FEN = generator.generateFEN(board)

    assertThat(FEN).isEqualTo(fenRead)
  }
}
