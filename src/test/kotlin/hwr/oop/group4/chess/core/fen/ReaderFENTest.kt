package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.pieces.Color

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class ReaderFENTest : AnnotationSpec() {

  @Test
  fun `piece placement list is created correctly`() {
    // Given
    val notationExample = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ReaderFEN(notationExample)

    // When
    val piecePlacement = fenOb.getPiecePlacement()
    val fenString = fenOb.notation

    // Then
    assertThat(fenString).isEqualTo(notationExample)
    assertThat(piecePlacement).isEqualTo(listOf("rnbqkbnr", "pp1ppppp", "8", "2p5", "4P3", "8", "PPPP1PPP", "RNBQKBNR"))
  }

  @Test
  fun `active color is read as white or black`() {
    // Given
    val notationExample1 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val notationExample2 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR b KQkq c6 0 2"
    val fenOb = ReaderFEN(notationExample1)
    val fenOb2 = ReaderFEN(notationExample2)

    // When
    val activeColor1 =fenOb.getActiveColor()
    val activeColor2 = fenOb2.getActiveColor()

    // Then
    assertThat(activeColor1).isEqualTo(Color.WHITE)
    assertThat(activeColor2).isEqualTo(Color.BLACK)
  }

  @Test
  fun `active color throws an error on wrong color`() {
    // Given
    val notationExample = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR t KQkq c6 0 2"
    val fenOb = ReaderFEN(notationExample)

    // Then
    assertThatThrownBy { fenOb.getActiveColor() }
      .isInstanceOf(InvalidColorException::class.java)
      .hasMessageContaining("Invalid color: t")
  }

  @Test
  fun `castle is read correctly`() {
    // Given
    val notationExample = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ReaderFEN(notationExample)

    // When
    val castle = fenOb.getCastle()

    // Then
    assertThat(castle).isEqualTo("KQkq")
  }

  @Test
  fun `en passant is read correctly`() {
    // Given
    val notationExample = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val fenOb = ReaderFEN(notationExample)

    // When
    val enPassant = fenOb.getEnPassant()

    // Then
    assertThat(enPassant).isEqualTo("c6")
  }

  @Test
  fun `half moves are read correctly`() {
    // Given
    val notationExample1 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    val notationExample2 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 1 2"
    val fenOb1 = ReaderFEN(notationExample1)
    val fenOb2 = ReaderFEN(notationExample2)

    // When
    val halfMoves1 = fenOb1.getHalfMoves()
    val halfMoves2 = fenOb2.getHalfMoves()

    // Then
    assertThat(halfMoves1).isEqualTo(0)
    assertThat(halfMoves2).isEqualTo(1)
  }

  @Test
  fun `full moves are read correctly`() {
    // Given
    val notationExample1 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 0"
    val notationExample2 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 1"
    val fenOb1 = ReaderFEN(notationExample1)
    val fenOb2 = ReaderFEN(notationExample2)

    // When
    val fullMoves1 = fenOb1.getFullMoves()
    val fullMoves2 = fenOb2.getFullMoves()

    // Then
    assertThat(fullMoves1).isEqualTo(0)
    assertThat(fullMoves2).isEqualTo(1)
  }
}
