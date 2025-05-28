package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.BlackPawn
import hwr.oop.group4.chess.core.pieces.Queen
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveTurnTest : AnnotationSpec() {

  private lateinit var game: Game

  @BeforeEach
  fun setup() {
    game = Game(TEST_NUMBER, EMPTY_BOARD)
  }

  @Test
  fun `white player cant move black pawn`() {
    // Given
    val blackPawn = BlackPawn(Color.BLACK)
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.A, Rank.ONE)
    game.board.setPieceToField(startLocation, blackPawn)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("You can not move a BLACK piece")
  }

  @Test
  fun `black player cant move white queen`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.A, Rank.ONE)
    game.board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("You can not move a WHITE piece")
  }
}
