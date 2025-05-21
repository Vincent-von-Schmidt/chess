package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.io.File


class CliMoveTest : AnnotationSpec() {

  private val storage = GameStorage()
  private val file = File(GAMES_FILE_TEST)

  @BeforeEach
  fun setup() {
    file.deleteRecursively()
  }

  @Test
  fun `user prompts valid move`() {
    // Given
    val game = Game(TEST_NUMBER)
    storage.saveGame(game)

    // When
    val output = captureStandardOut {
      main(arrayOf("on", "1000000", "move", "e2", "to", "e4"))
    }.trim()

    // Then
    assertThat(output).isEqualTo("Move from e2 to e4 executed.")
    assertThat(game.fen).isEqualTo("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")
  }

  @Test
  fun `user prompts invalid move`() {
    // Given
    val game = Game(TEST_NUMBER)
    storage.saveGame(game)

    // When
    val output = captureStandardOut {
      main(arrayOf("on", "1000000", "move", "e2", "to", "e5"))
    }.trim()

    // Then
    assertThat(output).isEqualTo("Invalid move from e2 to e5.")
    assertThat(game.fen).isEqualTo(STARTING_POSITION)
  }

  @Test
  fun `user prompts less than 6 args`() {
    // Then
    assertThatThrownBy { main(arrayOf("on", "1000000", "move", "e2", "to")) }
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
  fun `user prompts more than 6 args`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          "1000000",
          "move",
          "e2",
          "to",
          "e4",
          "extra"
        )
      )
    }
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
  fun `user prompts invalid id format`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          "invalid_id",
          "move",
          "e2",
          "to",
          "e4"
        )
      )
    }
      .hasMessage(
        """
        Error: <id> must be a valid integer!
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts invalid keyword move`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          "1000000",
          "invalid_keyword",
          "e2",
          "to",
          "e4"
        )
      )
    }
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
  fun `user prompts invalid keyword to`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          "1000000",
          "move",
          "e2",
          "invalid_keyword",
          "e4"
        )
      )
    }
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