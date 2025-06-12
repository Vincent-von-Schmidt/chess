package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TurnTest : AnnotationSpec() {

  @Test
  fun `test get turn`() {
    // Given
    val white = Player(1, Color.WHITE)
    val black = Player(2, Color.BLACK)

    // When
    val playerToMove = PlayerToMove(white, black, white)

    // Then
    assertThat(playerToMove.getCurrentColor()).isEqualTo(Color.WHITE)
  }

  @Test
  fun `test switch turn`() {
    // Given
    val white = Player(1, Color.WHITE)
    val black = Player(2, Color.BLACK)

    // When
    val playerToMove = PlayerToMove(white, black, white)
    playerToMove.switchTurn()

    // Then
    assertThat(playerToMove.getCurrentColor() == Color.BLACK)
  }
}