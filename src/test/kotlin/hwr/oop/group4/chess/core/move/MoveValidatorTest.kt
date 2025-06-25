package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveValidatorTest : AnnotationSpec() {

  @Test
  fun `Valid white pawn move from desired move`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(STARTING_POSITION)
    val startLocation = Location(File.E, Rank.TWO)
    val endLocation = Location(File.E, Rank.FOUR)

    // When
    val move = MoveDesired(startLocation, endLocation)
    val result = MoveDesiredValidator.validateMove(
      board,
      move,
      Color.WHITE
    )
    val validatedEndLocation = result.endLocation
    val validatedStartLocation = result.startLocation
    val validatedPieceToPlace = result.toPlacePiece.getDescription()

    // Then
    assertThat(validatedStartLocation).isEqualTo(startLocation)
    assertThat(validatedEndLocation).isEqualTo(endLocation)
    assertThat(validatedPieceToPlace).isEqualTo("WHITE Pawn")
  }
}
