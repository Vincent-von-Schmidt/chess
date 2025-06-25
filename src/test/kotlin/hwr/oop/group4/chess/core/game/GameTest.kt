package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GameTest : AnnotationSpec() {

  @BeforeEach
  fun setup() {
    GameStorage.deleteGame(Game(TEST_NUMBER))
  }

  @Test
  fun `create game`() {
    // Given
    val id = TEST_NUMBER

    // When
    val game = Game(id)

    // Then
    assertThat(game.id).isEqualTo(id)
    assertThat(game.getFen()).isEqualTo(STARTING_POSITION)
  }

  @Test
  fun `game board to string asciiArt`() {
    // Given
    val game = Game(TEST_NUMBER)
    val outputAsciiExpected = """
    r n b q k b n r
    p p p p p p p p
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    P P P P P P P P
    R N B Q K B N R""".trimIndent()

    // When
    val boardString = game.boardToAscii().trim()

    // Then
    assertThat(boardString).isEqualTo(outputAsciiExpected)
  }

  @Test
  fun `board to string after move asciiArt`() {
    // Given
    val game = Game(TEST_NUMBER)
    val moveDesired =
      MoveDesired(Location(File.E, Rank.TWO), Location(File.E, Rank.THREE))
    game.movePiece(moveDesired, promoteTo = null)
    val outputAsciiExpected = """
    r n b q k b n r
    p p p p p p p p
    - - - - - - - -
    - - - - - - - -
    - - - - - - - -
    - - - - P - - -
    P P P P - P P P
    R N B Q K B N R""".trimIndent()

    // When
    val boardStringAfterMove = game.boardToAscii().trim()

    // Then
    assertThat(boardStringAfterMove).isEqualTo(outputAsciiExpected)
  }

  @Test
  fun `game move piece`() {

    // Given
    val game = Game(TEST_NUMBER)
    val board = game.board
    val startLocation = Location(File.E, Rank.TWO)
    val endLocation = Location(File.E, Rank.THREE)
    val moveDesired =
      MoveDesired(startLocation, endLocation)

    // When
    game.movePiece(moveDesired)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(game.getFen()).isNotEqualTo(STARTING_POSITION)
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Pawn(Color.WHITE))
  }

  @Test
  fun `both players make turns asciiArt`() {
    // Given
    val game = Game(TEST_NUMBER)
    val moveDesiredWhite =
      MoveDesired(Location(File.E, Rank.TWO), Location(File.E, Rank.THREE))
    val moveDesiredBlack =
      MoveDesired(Location(File.E, Rank.SEVEN), Location(File.E, Rank.SIX))
    val outputAsciiExpected = """
    r n b q k b n r
    p p p p - p p p
    - - - - p - - -
    - - - - - - - -
    - - - - - - - -
    - - - - P - - -
    P P P P - P P P
    R N B Q K B N R""".trimIndent()

    // When
    game.movePiece(moveDesiredWhite, promoteTo = null)
    game.movePiece(moveDesiredBlack, promoteTo = null)
    val boardStringAfterMove = game.boardToAscii().trim()

    // Then
    assertThat(boardStringAfterMove).isEqualTo(outputAsciiExpected)
  }

  @Test
  fun `player get score on capture`() {
    // Given
    val game = Game(TEST_NUMBER)

    // Moves to free up diagonals and lines for captures
    val moves = listOf(
      MoveDesired(
        Location(File.E, Rank.TWO),
        Location(File.E, Rank.FOUR)
      ), // White
      MoveDesired(
        Location(File.D, Rank.SEVEN),
        Location(File.D, Rank.FIVE)
      ), // Black
      MoveDesired(
        Location(File.D, Rank.ONE),
        Location(File.G, Rank.FOUR)
      ), // White
      MoveDesired(
        Location(File.B, Rank.EIGHT),
        Location(File.C, Rank.SIX)
      ), // Black
      MoveDesired(
        Location(File.E, Rank.FOUR),
        Location(File.D, Rank.FIVE)
      ), // White pawn take pawn
      MoveDesired(
        Location(File.C, Rank.EIGHT),
        Location(File.G, Rank.FOUR)
      ), // black bishop takes queen
      MoveDesired(
        Location(File.H, Rank.TWO),
        Location(File.H, Rank.THREE)
      ), // white
      MoveDesired(
        Location(File.D, Rank.EIGHT),
        Location(File.D, Rank.SEVEN)
      ), // black
      MoveDesired(
        Location(File.D, Rank.FIVE),
        Location(File.C, Rank.SIX)
      ), // white pawn takes knight
      MoveDesired(
        Location(File.B, Rank.SEVEN),
        Location(File.C, Rank.SIX)
      ), // Black pawn takes pawn
      MoveDesired(
        Location(File.H, Rank.THREE),
        Location(File.G, Rank.FOUR)
      ), // white pawn takes bishop
      MoveDesired(
        Location(File.D, Rank.SEVEN),
        Location(File.D, Rank.EIGHT)
      ), // black
      MoveDesired(
        Location(File.H, Rank.ONE),
        Location(File.H, Rank.SEVEN)
      ), // white captures Pawn
      MoveDesired(
        Location(File.H, Rank.EIGHT),
        Location(File.H, Rank.SEVEN)
      ), // black captures rook
    )

    // When
    for (move in moves) {
      game.boardToAscii()
      game.movePiece(move)
    }

    // Then
    val blackPlayerScore = game.getPlayerScore(Color.BLACK)
    val whitePlayerScore = game.getPlayerScore(Color.WHITE)

    assertThat(blackPlayerScore).isEqualTo(15)
    assertThat(whitePlayerScore).isEqualTo(8)
  }
}