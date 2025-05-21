package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
import hwr.oop.group4.chess.persistence.GameStorage.GameAlreadyExistsException
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.io.File

class NewGameTest : AnnotationSpec() {

  private val file = File(GAMES_FILE_TEST)

  @BeforeEach
  fun setup() {
    file.deleteRecursively()
  }

  @Test
  fun `user prompts nothing`() {
    assertThatThrownBy { main(arrayOf()) }
      .isInstanceOf(NoCommandException::class.java)
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess new_game-`() {
    assertThatThrownBy { main(arrayOf("new_game")) }
      .isInstanceOf(NoCommandException::class.java)
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to>
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
      .isInstanceOf(GameAlreadyExistsException::class.java)
      .hasMessage("Game with ID 1000000 already exists.")
  }

  @Test
  fun `user prompts -chess new_gae-`() {
    assertThatThrownBy { main(arrayOf("new_gae")) }
      .isInstanceOf(NoCommandException::class.java)
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess new_game char-`() {
    assertThatThrownBy { main(arrayOf("new_game", "char")) }
      .isInstanceOf(WrongIdFormatException::class.java)
      .hasMessage(
        """
        Error: <id> must be a valid integer!
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess new_game 1 1-`() {
    assertThatThrownBy { main(arrayOf("new_game", "1", "1")) }
      .isInstanceOf(NoCommandException::class.java)
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to>
        """.trimIndent()
      )
  }
}
