package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TurnTest : AnnotationSpec() {

  @Test
  fun `test get turn`() {
    // Given
    val fen = STARTING_POSITION

    // When
    val turn = Turn(fen)

    // Then
    assertThat(turn.colorToMove).isEqualTo(Color.WHITE)
  }

  @Test
  fun `test switch turn`() {
    // Given
    val fen = STARTING_POSITION
    val turn = Turn(fen)

    // When
    turn.switchTurn()

    // Then
    assertThat(turn.colorToMove == Color.BLACK)
  }
}