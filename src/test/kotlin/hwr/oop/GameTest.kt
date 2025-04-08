package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GameTest : AnnotationSpec() {

    @Test
    fun `test game initiation with id`() {
        val id: Int = 1337
        val game: Game = Game(id)
        val gameId: Int = game.id
        assertThat(gameId).isEqualTo(id)
    }
}