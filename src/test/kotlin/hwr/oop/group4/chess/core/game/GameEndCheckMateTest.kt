package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.fen.FEN
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
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class GameEndCheckMateTest : AnnotationSpec() {

  @BeforeEach
  fun setup() {
    GameStorage.deleteGame(Game(TEST_NUMBER))
  }

  @Test
  fun `checkmate gameEnd`() {
    // Given
    val game = Game(
      TEST_NUMBER,
      fen = FEN("8/8/8//8/1r6/r7/5K2", Color.BLACK, "-", "-" ,0,0)
    )

    // When
    val startLocation = Location(File.B, Rank.THREE)
    val endLocation = Location(File.B, Rank.ONE)

    // Then
    assertThatThrownBy {
      game.movePiece(MoveDesired(startLocation, endLocation))
    }.hasMessage("The game ended in a CHECKMATE. The winner is BLACK")
  }

  @Test
  fun `checkmate gameEnd gets prevented by capture`() {
    // Given
    val game = Game(
      TEST_NUMBER,
      fen = FEN("8/8/8/3PPPr1/3PK3/3PPP1P/8/8", Color.BLACK, "-", "-" ,0,0)
    )
    val startLocation = Location(File.H, Rank.THREE)
    val endLocation = Location(File.G, Rank.FOUR)
    val pawn = Pawn(Color.WHITE)

    // When
    val moveDesiredBlack = MoveDesired(Location(File.G, Rank.FIVE), Location(File.G, Rank.FOUR))
    val moveDesiredWhite = MoveDesired(startLocation, endLocation)
    game.movePiece(moveDesiredBlack)
    game.movePiece(moveDesiredWhite)

    val pieceOnStartLocation = game.board.getPiece(startLocation)
    val pieceOnEndLocation = game.board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(pawn)
  }

  @Test
  fun `checkmate gameEnd gets prevented by block`() {
    // Given
    val game = Game(
      TEST_NUMBER,
      fen = FEN("8/8/8/3PPPr1/3PK3/3PPR2/8/8", Color.BLACK, "-", "-", 0, 0)
    )
    val startLocation = Location(File.F, Rank.THREE)
    val endLocation = Location(File.F, Rank.FOUR)
    val rook = Rook(Color.WHITE)

    // When
    val moveDesiredBlack =
      MoveDesired(Location(File.G, Rank.FIVE), Location(File.G, Rank.FOUR))
    val moveDesiredWhite = MoveDesired(startLocation, endLocation)
    game.movePiece(moveDesiredBlack)
    game.movePiece(moveDesiredWhite)

    val pieceOnStartLocation = game.board.getPiece(startLocation)
    val pieceOnEndLocation = game.board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }
}