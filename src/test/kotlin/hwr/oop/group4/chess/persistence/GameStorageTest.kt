package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class GameStorageTest : AnnotationSpec() {

  private val storage = GameStorage()
  private val file = java.io.File(GAMES_FILE_TEST)

  @BeforeEach
  fun setup() {
    file.deleteRecursively()
  }

  @AfterEach
  fun tearDown() {
    file.deleteRecursively()
  }

  @Test
  fun `games folder does not exist but is created`() {
    // Given
    val game = Game(TEST_NUMBER)

    // When
    storage.saveGame(game, newGame = true)

    // Then
    assertThat(file.exists()).isTrue
  }

  @Test
  fun `creating two games`() {
    // Given
    val game1 = Game(TEST_NUMBER)
    val game2 = Game(TEST_NUMBER + 1)

    // When
    storage.saveGame(game1, newGame = true)
    storage.saveGame(game2, newGame = true)

    // Then
    assertThat(file.readLines()).isEqualTo(
      listOf(
        "$TEST_NUMBER;${game1.fen}",
        "${TEST_NUMBER + 1};${game2.fen}"
      )
    )
  }

  @Test
  fun `creating a game with existing ID`() {
    // Given
    val game = Game(TEST_NUMBER)

    // When
    storage.saveGame(game, newGame = true)

    // Then
    assertThatThrownBy { storage.saveGame(game) }
      .hasMessage("Game with ID ${game.id} already exists.")
  }

  @Test
  fun `loading game that doesn't exist`() {
    // Given
    val game = Game(TEST_NUMBER)

    // Then
    assertThatThrownBy { storage.loadGame(game.id) }
      .hasMessage("Game with ID ${game.id} does not exist.")
  }

  @Test
  fun `loading game that exists`() {
    // Given
    val game = Game(TEST_NUMBER)
    storage.saveGame(game, newGame = true)

    // When
    val output = game.boardToAscii()

    // Then
    assertThat(output).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \nP P P P P P P P \nR N B Q K B N R \n")
  }

  @Test
  fun `deleting a game`() {
    // Given
    val game = Game(TEST_NUMBER)
    storage.saveGame(game, newGame = true)

    // When
    storage.deleteGame(game)

    // Then
    assertThatThrownBy { storage.loadGame(TEST_NUMBER) }
      .hasMessage("Game with ID $TEST_NUMBER does not exist.")
  }

  @Test
  fun `override game with new fen`() {
    // Given
    val game = Game(TEST_NUMBER)
    storage.saveGame(game, newGame = true)

    // When
    game.fen = "r1bqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R1BQKBNR w KQkq - 0 1"
    storage.saveGame(game, newGame = false)

    // Then
    val loadedGame = storage.loadGame(TEST_NUMBER)
    assertThat(loadedGame.fen).isEqualTo("r1bqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R1BQKBNR w KQkq - 0 1")
  }

  @Test
  fun `create two games and load the first`() {
    // Given
    val game1 = Game(TEST_NUMBER)
    val game2 = Game(TEST_NUMBER + 1)

    // When
    storage.saveGame(game1, newGame = true)
    storage.saveGame(game2, newGame = true)
    val output = game1.boardToAscii()

    // Then
    assertThat(output).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \nP P P P P P P P \nR N B Q K B N R \n")
  }

  @Test
  fun `save game to file that contains gibberish`() {
    // Given
    val game = Game(TEST_NUMBER)
    file.writeText("gibberish")

    // When
    storage.saveGame(game, newGame = true)

    // Then
    assertThat(file.readLines()).isEqualTo(
      listOf(
        "gibberish",
        "${game.id};${game.fen}"
      )
    )
  }

  @Test
  fun `load game that has difficult fen`() {
    // Given
    val fen = "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1 w KQkq c6 0 2"
    val game = Game(TEST_NUMBER, fen = fen)
    storage.saveGame(game, newGame = true)

    // When
    val output = game.boardToAscii()

    // Then
    assertThat(output).isEqualTo("r - b k - - - r \np - - p B p N p \nn - - - - n - - \n- p - N P - - P \n- - - - - - P - \n- - - P - - - - \nP - P - K - - - \nq - - - - - b - \n")
  }

  @Test
  fun `get normal game`() {
    // Given
    val fen = "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1 w KQkq c6 0 2"
    val game = Game(TEST_NUMBER, fen = fen)
    storage.saveGame(game, newGame = true)

    // When
    val gameLoaded = storage.loadGame(TEST_NUMBER)

    // Then
    assertThat(gameLoaded.id).isEqualTo(game.id)
    assertThat(gameLoaded.fen).isEqualTo(fen)
  }

}
