package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.game.Game
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveTurnTest : AnnotationSpec() {

  private lateinit var game: Game

  @Test
  fun `white player cant move black pawn`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/p7/8", Color.WHITE, "-", "-", 0, 1)
    game = Game(TEST_NUMBER, fen)
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.A, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(moveDesired)
    }.hasMessageContaining("You cannot move a BLACK Pawn")
  }

  @Test
  fun `black player cant move white queen`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/Q7/8", Color.BLACK, "-", "-", 0, 1)
    game = Game(TEST_NUMBER, fen)
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.A, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(moveDesired)
    }.hasMessageContaining("You cannot move a WHITE Queen")
  }
}
