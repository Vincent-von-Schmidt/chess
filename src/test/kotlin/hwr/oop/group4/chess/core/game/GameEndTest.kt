package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.cli.main
import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.pieces.Rook
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class GameEndTest : AnnotationSpec() {

  @Test
  fun `halfMove draw`() {
    // Given
    GameStorage.deleteGame(Game(TEST_NUMBER))
    val game = Game(
      TEST_NUMBER,
      fen = ParserFEN.parseStringToFen("8/8/8/1P1r4/8/8/8/8 b - - 49 0")
    )

    // When
    val moveDesired =
      MoveDesired(Location(File.D, Rank.FIVE), Location(File.C, Rank.FIVE))

    //Then
    assertThatThrownBy {
      game.movePiece(moveDesired)
    }.hasMessage("The game ended in a DRAW, because of FIFTY_MOVE_RULE")
  }

  @Test
  fun `halfMove reset on capture`() {
    // Given
    GameStorage.deleteGame(Game(TEST_NUMBER))
    val game = Game(
      TEST_NUMBER,
      fen = ParserFEN.parseStringToFen("8/8/8/1P1r4/8/8/8/8 b - - 49 0")
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
    GameStorage.deleteGame(Game(TEST_NUMBER))
    val game = Game(
      TEST_NUMBER,
      fen = ParserFEN.parseStringToFen("8/8/8/1P1r4/8/8/8/8 w - - 49 0")
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
  fun `checkmate gameEnd`() {
    // Given
    GameStorage.deleteGame(Game(TEST_NUMBER))
    val game = Game(
      TEST_NUMBER,
      ParserFEN.parseStringToFen("8/8/8/8/8/1r6/r7/5K2 b - - 0 0") // TODO make all fens in tests with parsers
    )

    // When
    val startLocation = Location(File.B, Rank.THREE)
    val endLocation = Location(File.B, Rank.ONE)

    // Then
    assertThatThrownBy {
      game.movePiece(MoveDesired(startLocation, endLocation))
    }.hasMessage("The game ended in a CHECKMATE, the winner is BLACK")
  }

  @Test
  fun `user makes draw move`() {
    // Given
    GameStorage.deleteGame(Game(TEST_NUMBER))
    val game = Game(
      TEST_NUMBER,
      ParserFEN.parseStringToFen("8/8/8/8/8/8/r7/R7 w - - 0 0")
    )
    GameStorage.saveGame(game) // TODO why have to manually save in order to work?

    // When
    val a1 = Location(File.A, Rank.ONE)
    val a2 = Location(File.A, Rank.TWO)
    val b1 = Location(File.B, Rank.ONE)
    val b2 = Location(File.B, Rank.TWO)
    game.movePiece(MoveDesired(a1, b1))
    game.movePiece(MoveDesired(a2, b2))
    game.movePiece(MoveDesired(b1, a1))
    game.movePiece(MoveDesired(b2, a2))

    game.movePiece(MoveDesired(a1, b1))
    game.movePiece(MoveDesired(a2, b2))
    game.movePiece(MoveDesired(b1, a1))

    val output = captureStandardOut {
      main(arrayOf("on", TEST_NUMBER.toString(), "move", "b2", "to", "a2"))
    }.trim()

    // Then
    assertThat(output).isEqualTo("The game ended in a DRAW, because of THREEFOLD_REPETITION")
  }
}