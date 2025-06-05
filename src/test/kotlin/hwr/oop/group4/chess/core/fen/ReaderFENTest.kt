package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.utils.Color

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class ReaderFENTest : AnnotationSpec() {

  @Test
  fun `piece placement list is created correctly`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ParserFEN.parseStringToFen(notationExample)
    assertThat(fenOb.piecePlacement).isEqualTo(
      listOf(
        "rnbqkbnr",
        "pp1ppppp",
        "8",
        "2p5",
        "4P3",
        "8",
        "PPPP1PPP",
        "RNBQKBNR"
      )
    )
  }

  @Test
  fun `active color is read as white`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ParserFEN.parseStringToFen(notationExample)
    assertThat(fenOb.activeColor).isEqualTo(Color.WHITE)
  }

  @Test
  fun `active color is read as black`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR b KQkq c6 0 2"
    val fenOb = ParserFEN.parseStringToFen(notationExample)
    assertThat(fenOb.activeColor).isEqualTo(Color.BLACK)
  }

  @Test
  fun `active color throws an error on wrong color`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR t KQkq c6 0 2"
    assertThatThrownBy { ParserFEN.parseStringToFen(notationExample) }.hasMessageContaining("Invalid color") // WRONG THO
  }

  @Test
  fun `castle is read correctly`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ParserFEN.parseStringToFen(notationExample)
    assertThat(fenOb.castle).isEqualTo("KQkq")
  }

  @Test
  fun `en passant is read correctly`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ParserFEN.parseStringToFen(notationExample)
    assertThat(fenOb.enPassant).isEqualTo("c6")
  }

  @Test
  fun `halfmoves are read correctly`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ParserFEN.parseStringToFen(notationExample)
    assertThat(fenOb.halfMoves).isEqualTo(0)
  }

  @Test
  fun `fullmove are read correctly`() {
    val notationExample =
      "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ParserFEN.parseStringToFen(notationExample)
    assertThat(fenOb.fullMoves).isEqualTo(2)
  }
}
