package hwr.oop.group4.chess.core.game

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class DrawExceptionTest : AnnotationSpec() {

  @Test
  fun `exception without input throws generic`() {
    assertThatThrownBy {
      throw DrawException(GameState.DRAW)
    }.hasMessage("The game ended in a DRAW")
  }
}