package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.game.GameFactory
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER_STRING
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy


class CliMoveTest : AnnotationSpec() {

  private val game =
    GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)

  @BeforeEach
  fun setup() {
    GameStorage.deleteGame(game.id)
  }

  @Test
  fun `valid move prompt creates AsciiArt`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER_STRING))
    val expectedAsciiArt = """
    r n b q k b n r
    p p p p p p p p
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    - - - - P - - -
    P P P P - P P P
    R N B Q K B N R
    BLACK to move.
    """.trimIndent()

    // When
    val outputMove = captureStandardOut {
      main(arrayOf("on", TEST_NUMBER_STRING, "move", "e2", "to", "e3"))
    }.trim()

    val outputShow = captureStandardOut {
      main(arrayOf("game", "show", TEST_NUMBER_STRING))
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from E2 to E3 executed.")
    assertThat(outputShow).isEqualTo(expectedAsciiArt)
  }

  @Test
  fun `invalid move prompt creates AsciiArt`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER_STRING))
    val expectedAsciiArt = """
    r n b q k b n r
    p p p p p p p p
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    P P P P P P P P
    R N B Q K B N R
    WHITE to move.
    """.trimIndent()

    // When
    val outputMove = captureStandardOut {
      main(arrayOf("on", TEST_NUMBER_STRING, "move", "e2", "to", "e5"))
    }.trim()

    val outputShow = captureStandardOut {
      main(arrayOf("game", "show", TEST_NUMBER_STRING))
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Invalid move from E2 to E5.")
    assertThat(outputShow).isEqualTo(expectedAsciiArt)
  }

  @Test
  fun `throw on prompt with less than 6 arguments`() {
    // Given
    val arguments = arrayOf("on", TEST_NUMBER_STRING, "move", "e2", "to")
    val errorMessage = """
    No valid command provided. Try one of the following:
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to> <promotion-title>
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `throw on prompts with more than 7 arguments`() {
    // Given
    val arguments = arrayOf(
      "on",
      TEST_NUMBER_STRING,
      "move",
      "e2",
      "to",
      "e4",
      "queen",
      "extra"
    )
    val errorMessage = """
    No valid command provided. Try one of the following:
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to> <promotion-title>
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `throw on prompt with invalid id`() {
    // Given
    val arguments = arrayOf("on", "invalid_id", "move", "e2", "to", "e4")
    val errorMessage = """
    Error: <id> must be a valid integer!
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `throw on prompt with invalid move keyword`() {
    // Given
    val arguments =
      arrayOf("on", TEST_NUMBER_STRING, "invalid_keyword", "e2", "to", "e4")
    val errorMessage = """
    No valid command provided. Try one of the following:
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to> <promotion-title>
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `throw on prompt with invalid to keyword`() {
    // Given
    val arguments =
      arrayOf(
        "on",
        TEST_NUMBER_STRING,
        "move",
        "e2",
        "invalid_keyword",
        "e4"
      )
    val errorMessage = """
    No valid command provided. Try one of the following:
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to> <promotion-title>
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `prompt with promotion to queen`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER_STRING))
    val arguments =
      arrayOf("on", TEST_NUMBER_STRING, "move", "a2", "to", "a4", "queen")

    // When
    val outputMove = captureStandardOut { main(arguments) }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `prompt with promotion to rook`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER_STRING))
    val arguments =
      arrayOf("on", TEST_NUMBER_STRING, "move", "a2", "to", "a4", "rook")

    // When
    val outputMove = captureStandardOut { main(arguments) }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `prompt with promotion to bishop`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER_STRING))
    val arguments =
      arrayOf("on", TEST_NUMBER_STRING, "move", "a2", "to", "a4", "bishop")

    // When
    val outputMove =
      captureStandardOut { main(arguments) }.trim() // TODO can we replace CaptureStandartOut?

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `prompt with promotion to knight`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER_STRING))
    val arguments =
      arrayOf("on", TEST_NUMBER_STRING, "move", "a2", "to", "a4", "knight")

    // When
    val outputMove = captureStandardOut { main(arguments) }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `throw on prompt with wrong promotion argument`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER_STRING))
    val arguments =
      arrayOf("on", TEST_NUMBER_STRING, "move", "a2", "to", "a4", "wrong")
    val errorMessage = """
    Valid Promotions are...
    ...Queen, Rook, Bishop, Knight.  
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }
}