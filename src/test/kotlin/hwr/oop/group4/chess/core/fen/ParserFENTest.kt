package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.utils.Color

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class ParserFENTest : AnnotationSpec() {

  val fen ="rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"

  @Test
  fun `piece placement list is created correctly`() {
    // Given
    val expectedPiecePlacement = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR"

    // When
    val parsedFenPiecePlacement = ParserFEN.parseStringToFen(fen).piecePlacement

    // Then
    assertThat(parsedFenPiecePlacement).isEqualTo(expectedPiecePlacement)
  }

  @Test
  fun `active color is read as white`() {
    // Given
    val expectedColor = Color.WHITE

    // When
    val parsedFenColor = ParserFEN.parseStringToFen(fen).activeColor

    // Then
    assertThat(parsedFenColor).isEqualTo(expectedColor)
  }

  @Test
  fun `active color is read as black`() {
    //Given
    val fen ="rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR b KQkq c6 0 2"
    val expectedColor = Color.BLACK

    //When
    val parsedFenColor = ParserFEN.parseStringToFen(fen).activeColor

    //Then
    assertThat(parsedFenColor).isEqualTo(expectedColor)
  }

  @Test
  fun `throw on wrong color`() {
    //Given
    val fen = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR o KQkq c6 0 2"

    // Then
    assertThatThrownBy { ParserFEN.parseStringToFen(fen) }.hasMessage(
      "Invalid color"
    )
  }

  @Test
  fun `castle is read correctly`() {
    // When
    val parsedFenCastle = ParserFEN.parseStringToFen(fen).castle

    // Then
    assertThat(parsedFenCastle).isEqualTo("KQkq")
  }

  @Test
  fun `en passant is read correctly`() {
    //When
    val parsedFenEnPassant = ParserFEN.parseStringToFen(fen).enPassant

    // Then
    assertThat(parsedFenEnPassant).isEqualTo("c6")
  }

  @Test
  fun `halfMoves are read correctly`() {
    // When
    val parsedFenHalfMoves = ParserFEN.parseStringToFen(fen).halfMoves

    // Then
    assertThat(parsedFenHalfMoves).isEqualTo(0)
  }

  @Test
  fun `fullMoves are read correctly`() {
    // When
    val parsedFenFullMoves = ParserFEN.parseStringToFen(fen).fullMoves

    //Then
    assertThat(parsedFenFullMoves).isEqualTo(2)
  }
}