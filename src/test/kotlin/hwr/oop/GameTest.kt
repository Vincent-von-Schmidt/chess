package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GameTest : AnnotationSpec() {

    @Ignore
    @Test
    fun `test game initiation with id`() {
        val id: Int = 10
        val game: Game = Game(id)
        val gameId = game.id
        assertThat(gameId).isEqualTo(id)
    }
}