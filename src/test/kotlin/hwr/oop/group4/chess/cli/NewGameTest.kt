package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class NewGameTest : AnnotationSpec() {

  private val game = Game(TEST_NUMBER)

  @BeforeEach
  fun setup() {
    GameStorage().deleteGame(game)
  }

  @Test
  fun `user prompts nothing`() {
    assertThatThrownBy { main(arrayOf()) }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess new_game-`() {
    assertThatThrownBy { main(arrayOf("new_game")) }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess new_game 1000000-`() {
    val output = captureStandardOut {
      main(arrayOf("new_game", "1000000"))
    }.trim()
    assertThat(output).isEqualTo("New game 1000000 created.")
  }

  @Test
  fun `user prompts -chess new_game 1000000- but the ID is already in use`() {
    main(arrayOf("new_game", "1000000"))
    assertThatThrownBy { main(arrayOf("new_game", "1000000")) }
      .hasMessage("Game with ID 1000000 already exists.")
  }

  @Test
  fun `user prompts -chess new_gae-`() {
    assertThatThrownBy { main(arrayOf("new_gae")) }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess new_game char-`() {
    assertThatThrownBy { main(arrayOf("new_game", "char")) }
      .hasMessage(
        """
        Error: <id> must be a valid integer!
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess new_game 1 1-`() {
    assertThatThrownBy { main(arrayOf("new_game", "1", "1")) }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }
}
