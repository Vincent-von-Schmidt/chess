package hwr.oop

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

    /*
    @Test
    fun `user prompts chess show game 1_000_000`() {
        // create game
        main(arrayOf("show_game", "1_000_000"))

        val output = captureStandardOut {
            main(arrayOf("game", "show", "1_000_000"))
        }.trim()
        assertThat(output).isEqualTo("""
            R N B K Q B N R
            P P P P P P P P
                        
            
            
            
            P P P P P P P P
            R N B K Q B N R
        """.trimIndent())
    }

    @Test
    fun `user prompts chess on 1_000_000 move e2 to e4`() {
        // create game
        main(arrayOf("new_game", "1_000_000"))

        val output = captureStandardOut {
            main(arrayOf("on", "1_000_000", "move", "e2", "to", "e4"))
        }.trim()
        assertThat(output).isEqualTo("""
            R N B K Q B N R
            P P P P P P P P
            
            
                    P
            
            P P P P   P P P
            R N B K Q B N R
        """.trimIndent())
    }

     */
}