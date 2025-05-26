package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
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
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(arrayOf("on", TEST_NUMBER.toString(), "move", "e2", "to", "e3"))
    }.trim()

    val outputShow = captureStandardOut {
      main(arrayOf("game", "show", TEST_NUMBER.toString()))
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from e2 to e3 executed.")
    val n = System.lineSeparator()
    assertThat(outputShow).isEqualTo("r n b q k b n r ${n}p p p p p p p p ${n}                ${n}                ${n}                ${n}        P       ${n}P P P P   P P P ${n}R N B Q K B N R ${n}")
  }

  @Test
  fun `user prompts invalid move`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(arrayOf("on", "1000000", "move", "e2", "to", "e5"))
    }.trim()

    val outputShow = captureStandardOut {
      main(arrayOf("game", "show", TEST_NUMBER.toString()))
    }.trim('\n')

    // Then
    assertThat(outputMove).isEqualTo("Invalid move from e2 to e5.")
    assertThat(outputShow).isEqualTo("r n b q k b n r \np p p p p p p p \n                \n                \n                \n                \nP P P P P P P P \nR N B Q K B N R \n")
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