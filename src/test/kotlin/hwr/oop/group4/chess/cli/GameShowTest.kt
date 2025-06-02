package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
import hwr.oop.group4.chess.persistence.GameStorage.GameDoesNotExistException
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.io.File

class GameShowTest : AnnotationSpec() {

  private val file = File(GAMES_FILE_TEST)

  @BeforeEach
  fun setup() {
    file.deleteRecursively()
  }

  @AfterEach
  fun tearDown() {
    file.deleteRecursively()
  }

  @Test
  fun `user prompts -chess game show-`() {
    assertThatThrownBy { main(arrayOf("game", "show")) }
      .isInstanceOf(InvalidCommandException::class.java)
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
  fun `user prompts -chess game show 1 1-`() {
    assertThatThrownBy { main(arrayOf("game", "show", "1", "1")) }
      .isInstanceOf(InvalidCommandException::class.java)
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
  fun `user prompts -chess game sow 1-`() {
    assertThatThrownBy { main(arrayOf("game", "sow", "1")) }
      .isInstanceOf(InvalidCommandException::class.java)
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
  fun `user prompts -chess game show char-`() {
    assertThatThrownBy { main(arrayOf("game", "show", "char")) }
      .isInstanceOf(WrongIdFormatException::class.java)
      .hasMessage(
        """
        Error: <id> must be a valid integer!
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts -chess game show 1000000-`() {
    main(arrayOf("new_game", "1000000"))
    val output = captureStandardOut {
      main(arrayOf("game", "show", "1000000"))
    }.trim()
    assertThat(output).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \nP P P P P P P P \nR N B Q K B N R \nWHITE to move.")
  }

  @Test
  fun `user prompts -chess game show 1000000- but ID is not created yet`() {
    assertThatThrownBy { main(arrayOf("game", "show", "1000000")) }
      .isInstanceOf(GameDoesNotExistException::class.java)
      .hasMessage("Game with ID 1000000 does not exist.")
  }
}
