package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.game.GameFactory
import hwr.oop.group4.chess.core.game.GameState
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.io.File as JavaFile

class GameStorageTest : AnnotationSpec() {

  private val storage = GameStorage

  @BeforeEach
  fun cleanUpTestGameFiles() {
    val directory = JavaFile("games")
    if (!directory.exists() || !directory.isDirectory) return
    directory.listFiles()?.forEach { file ->
      val match = Regex("""(\d+)\.csv""").matchEntire(file.name)
      val id = match?.groupValues?.get(1)?.toIntOrNull()
      if (id != null && id >= TEST_NUMBER) file.delete()
    }
  }

  @Test
  fun `creating two games and files exist`() {
    // Given
    val game1 = GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)
    val game2 = GameFactory.generateGameFromFen(TEST_NUMBER + 1, STARTING_POSITION)

    // When
    storage.saveGame(game1, true)
    storage.saveGame(game2, true)
    val file1 = JavaFile("games/${game1.id}.csv")
    val file2 = JavaFile("games/${game2.id}.csv")

    // Then
    assertThat(file1.exists()).isTrue
    assertThat(file2.exists()).isTrue
    assertThat(file1.readText()).isEqualTo("${game1.getFen()}\n")
    assertThat(file2.readText()).isEqualTo("${game2.getFen()}\n")
  }

  @Test
  fun `saving CHECK gameState`() {
    // Given
    val game = GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)
    val moves = listOf(
      MoveDesired(Location(File.E , Rank.TWO), Location(File.E , Rank.FOUR)),
      MoveDesired(Location(File.B , Rank.EIGHT), Location(File.C , Rank.SIX)),
      MoveDesired(Location(File.E , Rank.FOUR), Location(File.E , Rank.FIVE)),
      MoveDesired(Location(File.C , Rank.SIX), Location(File.D , Rank.FOUR)),
      MoveDesired(Location(File.E , Rank.FIVE), Location(File.E , Rank.SIX)),
      MoveDesired(Location(File.D , Rank.FOUR), Location(File.C , Rank.TWO)), // HERE SHOULD BE CHECK
      MoveDesired(Location(File.F , Rank.TWO), Location(File.F , Rank.THREE)),
    )

    // When
    for (move in moves) {
      game.movePiece(move)
    }
    val file = JavaFile("games/${game.id}.csv")
    val fileGameSaveEntryLast = file.useLines { it.lastOrNull() }
    val gameSaveEntry = game.getSaveEntries().last()

    // Then
    assertThat(file.exists()).isTrue
    assertThat(fileGameSaveEntryLast).isEqualTo("$gameSaveEntry")
  }

  @Test
  fun `creating a game with existing ID`() {
    // Given
    val game = GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)

    // When
    storage.saveGame(game, true)

    // Then
    assertThatThrownBy { storage.saveGame(game) }
      .hasMessage("Game with ID ${game.id} already exists.")
  }

  @Test
  fun `loading game that doesn't exist`() {
    // Given
    val game = GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)

    // Then
    assertThatThrownBy { storage.loadGame(game.id) }
      .hasMessage("Game with ID ${game.id} does not exist.")
  }

  @Test
  fun `loading game that exists`() {
    // Given
    val game = GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)
    storage.saveGame(game, true)
    val expectedAsciiArt = """
    r n b q k b n r
    p p p p p p p p
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    P P P P P P P P
    R N B Q K B N R
    
    """.trimIndent()

    // When
    val output = game.board.boardToAscii()

    // Then
    assertThat(output).isEqualTo(expectedAsciiArt)
  }

  @Test
  fun `deleting a game`() {
    // Given
    val game = GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)
    storage.saveGame(game, true)

    // When
    storage.deleteGame(TEST_NUMBER)

    // Then
    assertThatThrownBy { storage.loadGame(TEST_NUMBER) }
      .hasMessage("Game with ID $TEST_NUMBER does not exist.")
  }

  @Test
  fun `create two games and load the first`() {
    // Given
    val game1 = GameFactory.generateGameFromFen(TEST_NUMBER, STARTING_POSITION)
    val game2 = GameFactory.generateGameFromFen(TEST_NUMBER + 1, STARTING_POSITION)
    val expectedAsciiArt = """
    r n b q k b n r
    p p p p p p p p
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    P P P P P P P P
    R N B Q K B N R
    
    """.trimIndent()

    // When
    storage.saveGame(game1, true)
    storage.saveGame(game2, true) // TODO why newgame, when it can decide itself if the game exists already?
    val output = game1.board.boardToAscii()

    // Then
    assertThat(output).isEqualTo(expectedAsciiArt)
  }

  @Test
  fun `load game that has difficult fen`() {
    // Given
    val fen = FEN(
      "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1",
      Color.WHITE,
      "-",
      "-",
      0,
      2
    )
    val game = GameFactory.generateGameFromFen(TEST_NUMBER, fen)
    storage.saveGame(game, true)
    val expectedAsciiArt = """
    r - b k - - - r
    p - - p B p N p
    n - - - - n - -
    - p - N P - - P
    - - - - - - P -
    - - - P - - - -
    P - P - K - - -
    q - - - - - b -
    
    """.trimIndent()

    // When
    val output = game.board.boardToAscii()

    // Then
    assertThat(output).isEqualTo(expectedAsciiArt)
  }

  @Test
  fun `get normal game`() {
    // Given
    val fen = FEN(
      "r1bk3r/p2pBpNp/n4n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1",
      Color.WHITE,
      "-",
      "-",
      0,
      2
    )
    val game = GameFactory.generateGameFromFen(TEST_NUMBER, fen)
    storage.saveGame(game, true)

    // When
    val gameLoaded = storage.loadGame(TEST_NUMBER)

    // Then
    assertThat(gameLoaded.id).isEqualTo(game.id)
    assertThat(gameLoaded.getFen()).isEqualTo(fen)
  }

}
