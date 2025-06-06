package hwr.oop.group4.chess.core.utils

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class StringParserTest : AnnotationSpec() {

  @Test
  fun `input length is incorrect`() {
    // Given
    val tooLongInput = "A1B"

    // Then
    assertThatThrownBy { StringParser.parseLocationFromString(tooLongInput) }.hasMessage("Invalid location format: must be exactly 2 characters")
  }

  @Test
  fun `file character is invalid`() {
    // Given
    val invalidFileInput = "Z1"

    // Then
    assertThatThrownBy { StringParser.parseLocationFromString(invalidFileInput) }
      .hasMessage("Invalid file character: Z")
  }

  @Test
  fun `rank character is invalid`() {
    // Given
    val invalidRankInput = "A9"

    // Then
    assertThatThrownBy { StringParser.parseLocationFromString(invalidRankInput) }
      .hasMessage("Invalid rank character: 9")
  }

  @Test
  fun `parse illegal char into piece throws`() {
    // Given
    val invalidCharInput = 'z'

    // Then
    assertThatThrownBy { StringParser.parsePieceFromChar(invalidCharInput) }
      .hasMessage("Unknown char: $invalidCharInput")
  }
}