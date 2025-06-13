package hwr.oop.group4.chess.core.board

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class InvalidMoveExceptionTest : AnnotationSpec() {

  @Test
  fun `exception without input throws generic`() {
    assertThatThrownBy {
      throw InvalidMoveException()
    }.hasMessage("Invalid move")
  }
}