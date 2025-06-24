package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.game.Game
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
    GameStorage.deleteGame(game)
  }

  @Test
  fun `throw on empty prompt`() {
    // Given
    val arguments = emptyArray<String>()
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
  fun `throw on prompt -chess new_game-`() {
    // Given
    val arguments = arrayOf("new_game")
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
  fun `prompt -chess new_game 1000000-`() {
    // Given
    val arguments = arrayOf("new_game", "1000000")

    // When
    val output = captureStandardOut {
      main(arguments)
    }.trim()

    // Then
    assertThat(output).isEqualTo("New game 1000000 created.")
  }

  @Test
  fun `throw on prompt -chess new_game 1000000- but the ID is already in use`() {
    // Given
    val arguments = arrayOf("new_game", "1000000")
    main(arguments)

    //Then
    assertThatThrownBy { main(arguments) }
      .hasMessage("Game with ID 1000000 already exists.")
  }

  @Test
  fun `throw on prompt -chess new_gae-`() {
    // Given
    val arguments = arrayOf("new_gae")
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
  fun `throw on prompt -chess new_game char-`() {
    // Given
    val arguments = arrayOf("new_game", "char")
    val errorMessage = """
    Error: <id> must be a valid integer!
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }
      .hasMessage(errorMessage)
  }

  @Test
  fun `throw on prompt -chess new_game 1 1-`() {
    // Given
    val arguments = arrayOf("new_game", "1", "1")
    val errorMessage = """
    No valid command provided. Try one of the following:
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to> <promotion-title>
    """.trimIndent()

    // Then
    assertThatThrownBy { main(arguments) }.hasMessage(errorMessage)
  }
}
