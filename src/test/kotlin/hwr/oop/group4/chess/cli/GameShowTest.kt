package cli

import persistence.GameStorage

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat

class GameShowTest : AnnotationSpec() {

    private val storage = GameStorage()

    @Test
    fun `user prompts -chess game show-`() {
        val output = captureStandardOut {
            main(arrayOf("game", "show"))
        }.trim()
        assertThat(output).isEqualTo("Usage: chess game show <id>")
    }

    @Test
    fun `user prompts -chess game show 1 1-`() {
        val output = captureStandardOut {
            main(arrayOf("game", "show", "1", "1"))
        }.trim()
        assertThat(output).isEqualTo("Error: game show only takes 1 parameter!")
    }

    @Test
    fun `user prompts -chess game sow 1-`() {
        val output = captureStandardOut {
            main(arrayOf("game", "sow", "1"))
        }.trim()
        assertThat(output).isEqualTo("Usage: chess game show <id>")
    }

    @Test
    fun `user prompts -chess game show char-`() {
        val output = captureStandardOut {
            main(arrayOf("game", "show", "char"))
        }.trim()
        assertThat(output).isEqualTo("Error: game ID must be a valid integer!")
    }

    @Test
    fun `user prompts -chess game show 1000000-`() {
        storage.deleteGame(1000000)
        main(arrayOf("new_game", "1000000"))
        val output = captureStandardOut {
            main(arrayOf("game", "show", "1000000"))
        }.trim()
        assertThat(output).isEqualTo(
        """
        r n b q k b n r
        p p p p p p p p
                       
                       
                       
                       
        P P P P P P P P
        R N B Q K B N R
        """.trimIndent())
        storage.deleteGame(1000000)
    }

    @Test
    fun `user prompts -chess game show 1000000- but ID is not created yet`() {
        val output = captureStandardOut {
            storage.deleteGame(1000000)
            main(arrayOf("game", "show", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("Error: game ID is not in use!")
    }
}