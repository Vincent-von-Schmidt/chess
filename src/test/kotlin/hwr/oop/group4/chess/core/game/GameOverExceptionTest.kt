package hwr.oop.group4.chess.core.game

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class GameOverExceptionTest : AnnotationSpec() {

  @Test
  fun `exception without input throws generic`() {
    assertThatThrownBy {
      throw GameOverException(
        endReason = GameState.CHECKMATE,
        drawReason = null,
        winner = null
      )
    }.hasMessage("The game ended in a CHECKMATE")
  }
}