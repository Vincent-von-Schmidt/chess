package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.cli.main
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.pieces.Rook
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER_STRING
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class GameEndDrawTest : AnnotationSpec() {

  @BeforeEach
  fun setup() {
    GameStorage.deleteGame(TEST_NUMBER)
  }

  @Test
  fun `halfMove draw`() {
    // Given
    val game = GameFactory.generateGameFromFen(
      TEST_NUMBER,
      toLoadFen = FEN("8/8/8/1P1r4/8/8/8/8", Color.BLACK, "-", "-", 49, 0)
    )

    // When
    val moveDesired =
      MoveDesired(Location(File.D, Rank.FIVE), Location(File.C, Rank.FIVE))

    //Then
    assertThatThrownBy {
      game.movePiece(moveDesired)
    }.hasMessage("The game ended in a DRAW, due to FIFTY_MOVE_RULE")
  }

  @Test
  fun `halfMove reset on capture`() {
    // Given
    val game = GameFactory.generateGameFromFen(
      TEST_NUMBER,
      toLoadFen = FEN("8/8/8/1P1r4/8/8/8/8", Color.BLACK, "-", "-", 49, 0)
    )
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.B, Rank.FIVE)
    val rook = Rook(Color.BLACK)

    // When
    game.movePiece(MoveDesired(startLocation, endLocation))
    val pieceOnStartLocation = game.board.getPiece(startLocation)
    val pieceOnEndLocation = game.board.getPiece(endLocation)

    //Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }

  @Test
  fun `halfMove reset on pawnMove`() {
    // Given
    val game = GameFactory.generateGameFromFen(
      TEST_NUMBER,
      toLoadFen = FEN("8/8/8/1P1r4/8/8/8/8", Color.WHITE, "-", "-", 49, 0)
    )
    val startLocation = Location(File.B, Rank.FIVE)
    val endLocation = Location(File.B, Rank.SIX)
    val pawn = Pawn(Color.WHITE)

    // When
    game.movePiece(MoveDesired(startLocation, endLocation))
    val pieceOnStartLocation = game.board.getPiece(startLocation)
    val pieceOnEndLocation = game.board.getPiece(endLocation)

    //Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(pawn)
  }

  @Test
  fun `threefold repetition draw`() {
    // Given
    val game = GameFactory.generateGameFromFen(
      TEST_NUMBER,
      toLoadFen = FEN("8/8/8/8/8/8/r7/R7", Color.WHITE, "-", "-", 0, 0)
    )
    GameStorage.saveGame(game)

    val a1 = Location(File.A, Rank.ONE)
    val a2 = Location(File.A, Rank.TWO)
    val b1 = Location(File.B, Rank.ONE)
    val b2 = Location(File.B, Rank.TWO)

    val moves = listOf(
      MoveDesired(a1, b1),
      MoveDesired(a2, b2),
      MoveDesired(b1, a1),
      MoveDesired(b2, a2),
      MoveDesired(a1, b1),
      MoveDesired(a2, b2),
      MoveDesired(b1, a1)

    )
    moves.forEach {
      game.movePiece(it)
    }

    val arguments = arrayOf("on", TEST_NUMBER_STRING, "move", "b2", "to", "a2")

    //When
    val output = captureStandardOut { main(arguments) }.trim()

    // Then
    assertThat(output).isEqualTo("The game ended in a DRAW, due to THREEFOLD_REPETITION")
  }
}