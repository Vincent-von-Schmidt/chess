package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class PlayerTest : AnnotationSpec() {

  @Test
  fun `player creation`() {
    // Given
    val id = 1
    val color = Color.WHITE

    // When
    val player = Player(id, color)

    // Then
    assertThat(player.getId()).isEqualTo(id)
    assertThat(player.getColor()).isEqualTo(color)
  }
}