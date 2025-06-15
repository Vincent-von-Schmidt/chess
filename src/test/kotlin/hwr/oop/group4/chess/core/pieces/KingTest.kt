package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class KingTest : AnnotationSpec() {

  @Test
  fun `value of King, since cant be captured is 0`() {
    val kingValue = King(Color.BLACK).getValue()
    assertThat(kingValue).isEqualTo(0)
  }
}