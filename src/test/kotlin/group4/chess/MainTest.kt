package group4.chess

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat

class MainTest : AnnotationSpec() {

    @Test
    fun `user prompts nothing`() {
        val output = captureStandardOut {
            main(arrayOf())
        }.trim()
        assertThat(output).isEqualTo("No command provided. Try: chess new_game <id>")
    }

    @Test
    fun `user prompts chess new_game`() {
        val output = captureStandardOut {
            main(arrayOf("new_game"))
        }.trim()
        assertThat(output).isEqualTo("Usage: chess new_game <id>")
    }

    @Test
    fun `user prompts chess new_game 1000000`() {
        val output = captureStandardOut {
            main(arrayOf("new_game", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("New game 1000000 created.")
    }

    @Test
    fun `user prompts -chess new_game 1000000- but id is already in use`() {
        // create game
        main(arrayOf("new_game", "1000000"))

        val output = captureStandardOut {
            main(arrayOf("new_game", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("Error: game ID is already in use!")
    }

    @Test
    fun `user prompts chess new_gae`() {
        val output = captureStandardOut {
            main(arrayOf("new_gae"))
        }.trim()
        assertThat(output).isEqualTo("Error: unknown command \"new_gae\"")
    }

    @Test
    fun `user prompts chess new_game char`() {
        val output = captureStandardOut {
            main(arrayOf("new_game", "char"))
        }.trim()
        assertThat(output).isEqualTo("Error: new_game ID must be a valid integer!")
    }

    @Test
    fun `user prompts new_game 1000000 1000000`() {
        val output = captureStandardOut {
            main(arrayOf("new_game", "1000000", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("Error: new_game only takes 1 parameter!")
    }

    @Ignore
    @Test
    fun `user prompts chess game show 1000000`() {
        // create game
        main(arrayOf("new_game", "1000000"))

        val output = captureStandardOut {
            main(arrayOf("game", "show", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("""
            r n b q k b n r
            p p p p p p p p
                        
            
            
            
            P P P P P P P P
            R N B Q K B N R
        """.trimIndent())
    }

    @Ignore
    @Test
    fun `user prompts -chess game show 1000000- id is not created yet`() {
        val output = captureStandardOut {
            main(arrayOf("game", "show", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("Error: game ID does not exist!")
    }

    @Test
    fun `user prompts -chess game sow 1000000`() {
        val output = captureStandardOut {
            main(arrayOf("game", "sow", "1000000"))
        }.trim()
        assertThat(output).isEqualTo("Usage: chess game show <id>")
    }

    @Test
    fun `user prompts -chess game show char`() {
        val output = captureStandardOut {
            main(arrayOf("game", "show", "char"))
        }.trim()
        assertThat(output).isEqualTo("Error: game ID must be a valid integer!")
    }

    @Ignore
    @Test
    fun `user prompts chess on 1000000 move e2 to e4`() {
        // create game
        main(arrayOf("new_game", "1000000"))

        val output = captureStandardOut {
            main(arrayOf("on", "1000000", "move", "e2", "to", "e4"))
        }.trim()
        assertThat(output).isEqualTo("""
            r n b q k b n r
            p p p p p p p p
                        
            
                    P
            
            P P P P   P P P
            R N B Q K B N R
        """.trimIndent())
    }
}
