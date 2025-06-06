package hwr.oop.group4.chess.core.utils

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class ColorTest : AnnotationSpec() {

  @Test
  fun `opposite of black is white`() {
    // Given
    val opposite = Color.BLACK

    // When
    val colorOpposite = Color.WHITE.opposite()

    // Then
    assertThat(colorOpposite).isEqualTo(opposite)
  }

  @Test
  fun `opposite of white is black`() {
    // Given
    val opposite = Color.WHITE

    // When
    val colorOpposite = Color.BLACK.opposite()

    // Then
    assertThat(colorOpposite).isEqualTo(opposite)
  }
}