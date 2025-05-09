package group4.chess.cliConnection

import group4.chess.GameStorage
import group4.chess.cli.main
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat

class NewGameTest : AnnotationSpec() {

    private val storage = GameStorage()

    @Test
    fun `user prompts nothing`() {
        val output = captureStandardOut {
            main(arrayOf())
        }.trim()
        assertThat(output).isEqualTo("No command provided. Try: chess new_game <id>")
    }

    @Test
    fun `user prompts -chess new_game-`() {
        val output = captureStandardOut {
            main(arrayOf("new_game"))
        }.trim()
        assertThat(output).isEqualTo("Usage: chess new_game <id>")
    }

    @Test
    fun `user prompts -chess new_game 1000000-`() {
        storage.deleteGame(1000000)
        val output = captureStandardOut {
            main(arrayOf("new_game", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("New game 1000000 created.")
        storage.deleteGame(1000000)
    }

    @Test
    fun `user prompts -chess new_game 1000000- but the ID is already in use`() {
        storage.deleteGame(1000000)
        main(arrayOf("new_game", "1000000"))
        val output = captureStandardOut {
            main(arrayOf("new_game", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("Error: game ID is already in use")
        storage.deleteGame(1000000)
    }

    @Test
    fun `user prompts -chess new_gae-`() {
        val output = captureStandardOut {
            main(arrayOf("new_gae"))
        }.trim()
        assertThat(output).isEqualTo("Error: unknown command \"new_gae\"")
    }

    @Test
    fun `user prompts -chess new_game char-`() {
        val output = captureStandardOut {
            main(arrayOf("new_game", "char"))
        }.trim()
        assertThat(output).isEqualTo("Error: new_game ID must be a valid integer!")
    }

    @Test
    fun `user prompts -new_game 1 1-`() {
        val output = captureStandardOut {
            main(arrayOf("new_game", "1", "1"))
        }.trim()
        assertThat(output).isEqualTo("Error: new_game only takes 1 parameter!")
    }
}
