package hwr.oop.group4.chess.core.board

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class NoPieceExceptionTest : AnnotationSpec() {

  @Test
  fun `exception without input throws generic`() {
    assertThatThrownBy {
      throw NoPieceException()
    }.hasMessage("Required piece not found")
  }
}