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

class GameShowTest : AnnotationSpec() {

  private val game =
    GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)

  @BeforeEach
  fun setup() {
    GameStorage.deleteGame(game.id)
  }

  @Test
  fun `throw on prompt -chess game show-`() {
    // Given
    val arguments = arrayOf("game", "show")
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
  fun `throw on prompt -chess game show 1 1-`() {
    // Given
    val arguments = arrayOf("game", "show", "1", "1")
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
  fun `throw on prompt -chess game sow 1-`() {
    // Given
    val arguments = arrayOf("game", "sow", "1")
    val errorMessage = """
    No valid command provided. Try one of the following:
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to> <promotion-title>
    """.trimIndent()

    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `throw on prompt -chess game show char-`() {
    // Given
    val arguments = arrayOf("game", "show", "char")
    val errorMessage = """
    Error: <id> must be a valid integer!
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `prompt -chess game show 1000000-`() {
    //Given
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
    main(arrayOf("new_game", TEST_NUMBER_STRING))

    // When
    val output = captureStandardOut {
      main(arrayOf("game", "show", TEST_NUMBER_STRING))
    }.trim()

    // Then
    assertThat(output).isEqualTo(expectedAsciiArt)
  }

  @Test
  fun `prompt -chess game show 1000000- but ID is not created yet`() {
    // Given
    val arguments = arrayOf("game", "show", TEST_NUMBER_STRING)

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage("Game with ID 1000000 does not exist.")
  }
}